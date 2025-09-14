package org.zeppy.flango.FizzleBlock;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;
import org.zeppy.flango.core.FlangoBlockEntities;
import org.zeppy.flango.core.ImplementedInventory;

public class FizzleBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(9, ItemStack.EMPTY);

    public FizzleBlockEntity(BlockPos pos, BlockState state) {
        super(FlangoBlockEntities.FIZZLE_BLOCK_ENTITY, pos, state);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    public Text getDisplayName() {
        return Text.literal("Fizzle Block Inventory");
    }

    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new FizzleBlockScreenHandler(syncId, playerInventory, this);
    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        return true;
    }

    @Override
    protected void writeData(WriteView writeView) {
        Inventories.writeData(writeView, inventory);

        super.writeData(writeView);
    }

    @Override
    protected void readData(ReadView readView) {
        super.readData(readView);
        Inventories.readData(readView, inventory);
    }
}
