package org.zeppy.flango.core;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;

public interface ImplementedInventory extends Inventory {

    /** Returns the backing list of items */
    DefaultedList<ItemStack> getItems();

    @Override
    default int size() {
        return getItems().size();
    }

    @Override
    default boolean isEmpty() {
        for (ItemStack stack : getItems()) {
            if (!stack.isEmpty()) return false;
        }
        return true;
    }

    @Override
    default ItemStack getStack(int slot) {
        return getItems().get(slot);
    }

    @Override
    default ItemStack removeStack(int slot) {
        return removeStack(slot, 1);
    }

    @Override
    default ItemStack removeStack(int slot, int amount) {
        return net.minecraft.inventory.Inventories.splitStack(getItems(), slot, amount);
    }

    @Override
    default void setStack(int slot, ItemStack stack) {
        getItems().set(slot, stack);
    }

    @Override
    default void clear() {
        getItems().clear();
    }
}
