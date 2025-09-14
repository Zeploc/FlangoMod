package org.zeppy.flango.core;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import org.zeppy.flango.FizzleBlock.FizzleBlock;

import java.util.function.Function;

public class FlangoBlocks {
    public static final Block FIZZLE_BLOCK = register("fizzle_block", FizzleBlock::new, Block.Settings.create().strength(4.0f)
            .sounds(BlockSoundGroup.ANCIENT_DEBRIS)
            .luminance(FizzleBlock::getLuminance));
    public static final Block FLINGO_BLOCK = register("flingo_block", Block::new, Block.Settings.create().strength(4.0f).sounds(BlockSoundGroup.AMETHYST_BLOCK));

    public static final RegistryKey<ItemGroup> CUSTOM_ITEM_GROUP_KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(), Identifier.of(FlangoMod.MOD_ID, "item_group"));
//    public static final ItemGroup CUSTOM_ITEM_GROUP = FabricItemGroup.builder()
//            .icon(() -> new ItemStack(ModItems.GUIDITE_SWORD))
//            .displayName(Text.translatable("itemGroup.fabric_docs_reference"))
//            .build();

    private static Block register(String path, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings) {
        final Identifier identifier = Identifier.of(FlangoMod.MOD_ID, path);
        final RegistryKey<Block> registryKey = RegistryKey.of(RegistryKeys.BLOCK, identifier);

        final Block block = Blocks.register(registryKey, factory, settings);
        Items.register(block);
        return block;
    }

    public static void initialize() {
//        Registry.register(Registries.ITEM_GROUP, CUSTOM_ITEM_GROUP_KEY, CUSTOM_ITEM_GROUP);

        ItemGroupEvents.modifyEntriesEvent(CUSTOM_ITEM_GROUP_KEY).register((itemGroup) -> {
            itemGroup.add(FIZZLE_BLOCK.asItem());
            itemGroup.add(FLINGO_BLOCK.asItem());
        });
    }
}
