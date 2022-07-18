package com.github.klyser8.eggstra.registry;

import com.github.klyser8.eggstra.dispenser.GoldenEggDispenserBehavior;
import com.github.klyser8.eggstra.item.GoldenEggItem;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class EggstraDispenserBehaviors {

    public static final DispenseItemBehavior SHOOT_GOLDEN_EGG = new GoldenEggDispenserBehavior();

    // get custom dispenser behavior
    // this checks conditions such as the item and certain block or entity being in front of the dispenser to decide which rule to return
    // if the conditions for the rule match, it returns the instance of the dispenser behavior
    // returns null to fallback to vanilla (or another mod's) behavior for the given item
    public static DispenseItemBehavior getCustomDispenserBehavior(ItemStack stack) {
        Item item = stack.getItem();
        if (item instanceof GoldenEggItem) {
            return SHOOT_GOLDEN_EGG;
        }
        return null;
    }

}
