package io.github.duskitty.kittycamp.init;

import io.github.duskitty.kittycamp.Main;
import net.minecraft.item.BlockItem;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.FoodComponents;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Item_Init {
    public static final Item STICK_NOMALLOW = new BlockItem(Block_Init.NOMALLOWSTICK ,new Item.Settings().group(Main.KITTYCAMP));
    public static final Item STICK_MALLOW = new BlockItem(Block_Init.MALLOWSTICK ,new Item.Settings().group(Main.KITTYCAMP));
    public static final Item STICK_COOKED = new BlockItem(Block_Init.COOKEDMALLOWSTICK ,new Item.Settings().group(Main.KITTYCAMP));
    public static final Item STICK_BURNED = new BlockItem(Block_Init.BURNEDMALLOWSTICK ,new Item.Settings().group(Main.KITTYCAMP));
    public static final Item CHOCOLATE = new Item(new Item.Settings().group(Main.KITTYCAMP).food((new FoodComponent.Builder()).hunger(2).saturationModifier(1F).build()));
    public static final Item GRAHMCRACKER = new Item(new Item.Settings().group(Main.KITTYCAMP).food((new FoodComponent.Builder()).hunger(1).saturationModifier(2F).build()));
    public static final Item MALLOW_PLANT = new BlockItem(Block_Init.MALLOWPLANT ,new Item.Settings().group(Main.KITTYCAMP));
    public static final Item MARSHMELLOW = new Item(new Item.Settings().group(Main.KITTYCAMP).food((new FoodComponent.Builder()).hunger(2).saturationModifier(1F).build()));
    public static final Item MARSHMELLOW_COOKED = new Item(new Item.Settings().group(Main.KITTYCAMP).food((new FoodComponent.Builder()).hunger(3).saturationModifier(2F).build()));
    public static final Item SMORE = new Item(new Item.Settings().group(Main.KITTYCAMP).food((new FoodComponent.Builder()).hunger(5).saturationModifier(8F).build()));

    public static void InitItems(){
        Registry.register(Registry.ITEM, new Identifier("kittycamp", "stick_nomallow"), STICK_NOMALLOW);
        Registry.register(Registry.ITEM, new Identifier("kittycamp", "stick_mallow"), STICK_MALLOW);
        Registry.register(Registry.ITEM, new Identifier("kittycamp", "stick_cooked"), STICK_COOKED);
        Registry.register(Registry.ITEM, new Identifier("kittycamp", "stick_burned"), STICK_BURNED);
        Registry.register(Registry.ITEM, new Identifier("kittycamp", "chocolate"), CHOCOLATE);
        Registry.register(Registry.ITEM, new Identifier("kittycamp", "grahmcracker"), GRAHMCRACKER);
        Registry.register(Registry.ITEM, new Identifier("kittycamp", "mallow_plant"), MALLOW_PLANT);
        Registry.register(Registry.ITEM, new Identifier("kittycamp", "marshmallow"), MARSHMELLOW);
        Registry.register(Registry.ITEM, new Identifier("kittycamp", "marshmallow_cooked"), MARSHMELLOW_COOKED);
        Registry.register(Registry.ITEM, new Identifier("kittycamp", "smore"), SMORE);
    }
}
