package org.zeppy.flango.FizzleBlock;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class FizzleBlock extends BlockWithEntity {
    static final int PRIMES_REQUIRED = 10;

    public static final BooleanProperty PRIMED = BooleanProperty.of("primed");
    public static final IntProperty PRIMED_AMOUNT = IntProperty.of("primed_amount", 0, PRIMES_REQUIRED);
    public static final IntProperty FIZZLE_Strength = IntProperty.of("fizzle_strength", 1, 5);

    public FizzleBlock(Settings settings) {
        super(settings);

        // Default states
        setDefaultState(getDefaultState().with(PRIMED, false));
        setDefaultState(getDefaultState().with(PRIMED_AMOUNT, 0));
        setDefaultState(getDefaultState().with(FIZZLE_Strength, 1));
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return createCodec(FizzleBlock::new);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(PRIMED);
        builder.add(PRIMED_AMOUNT);
        builder.add(FIZZLE_Strength);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        ItemStack stack = player.getMainHandStack();


        if (!player.getAbilities().allowModifyWorld) {
            // Skip if the player isn't allowed to modify the world.
            return ActionResult.PASS;
        }

        if (!world.isClient) {
            if (!player.isSneaking() && !stack.isEmpty() && stack.isOf(Items.FLINT)) {
                // Check already max
                int primed_count = state.get(PRIMED_AMOUNT);
                if (primed_count >= PRIMES_REQUIRED) {
                    world.playSound(null, pos, SoundEvents.BLOCK_NOTE_BLOCK_PLING.value(), SoundCategory.BLOCKS, 1f, 1f);
                    player.sendMessage(Text.literal("Fully primed!"), true);
                    return ActionResult.PASS;
                }

                // Increase current primed amount
                stack.decrement(1);
                primed_count++;
                BlockState newBlockState = state.with(PRIMED_AMOUNT, primed_count);

                player.sendMessage(Text.literal("Priming to " + primed_count + "!"), true);
                float percent = (float) primed_count / (float)PRIMES_REQUIRED;
                world.playSound(null, pos,
                        SoundEvents.BLOCK_COPPER_PLACE, SoundCategory.PLAYERS,
                        1f, percent);
                if (primed_count == PRIMES_REQUIRED) {
                    world.playSound(null, pos, SoundEvents.BLOCK_NOTE_BLOCK_BELL.value(), SoundCategory.BLOCKS, 1f, 1f);
                    newBlockState.with(PRIMED, true);
                }
                world.setBlockState(pos, newBlockState);

                return ActionResult.SUCCESS;
            }
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof FizzleBlockEntity fizzleBlockEntity) {
                player.openHandledScreen(fizzleBlockEntity); // opens GUI
            }

        }


        return ActionResult.SUCCESS;
    }

    public static int getLuminance(BlockState currentBlockState) {
        // Get the value of the "primed_count" property.
        boolean activated = currentBlockState.get(FizzleBlock.PRIMED);

        // Return a light level if primed_count = true
        return activated ? 15 : 0;
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new FizzleBlockEntity(pos, state);
    }
}
