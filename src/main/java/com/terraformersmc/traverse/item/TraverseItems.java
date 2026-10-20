package com.terraformersmc.traverse.item;

import com.terraformersmc.traverse.block.TraverseBlocks;
import com.terraformersmc.traverse.init.helpers.TraverseRegistry;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;

public class TraverseItems {
	public static BlockItem RED_AUTUMNAL_LEAVES;
	public static BlockItem RED_AUTUMNAL_SAPLING;
	public static BlockItem BROWN_AUTUMNAL_LEAVES;
	public static BlockItem BROWN_AUTUMNAL_SAPLING;
	public static BlockItem ORANGE_AUTUMNAL_LEAVES;
	public static BlockItem ORANGE_AUTUMNAL_SAPLING;
	public static BlockItem YELLOW_AUTUMNAL_LEAVES;
	public static BlockItem YELLOW_AUTUMNAL_SAPLING;

	public static BlockItem FIR_LEAVES;
	public static BlockItem FIR_SAPLING;

	public static BlockItem FIR_LOG;
	public static BlockItem FIR_WOOD;
	public static BlockItem STRIPPED_FIR_LOG;
	public static BlockItem STRIPPED_FIR_WOOD;
	public static BlockItem FIR_PLANKS;
	public static BlockItem FIR_SLAB;
	public static BlockItem FIR_PRESSURE_PLATE;
	public static BlockItem FIR_FENCE;
	public static BlockItem FIR_TRAPDOOR;
	public static BlockItem FIR_FENCE_GATE;
	public static BlockItem FIR_STAIRS;
	public static BlockItem FIR_BUTTON;
	public static BlockItem FIR_DOOR;
	public static BlockItem FIR_SHELF;

	public static StandingAndWallBlockItem FIR_SIGN;
	public static HangingSignItem FIR_HANGING_SIGN;

	public static void register() {
		RED_AUTUMNAL_LEAVES = TraverseRegistry.registerBlockItem("red_autumnal_leaves", TraverseBlocks.RED_AUTUMNAL_LEAVES, new Item.Properties().compostable(ContextIntProviders.COMPOSTABLE_LOW));
		RED_AUTUMNAL_SAPLING = TraverseRegistry.registerBlockItem("red_autumnal_sapling", TraverseBlocks.RED_AUTUMNAL_SAPLING, new Item.Properties().compostable(ContextIntProviders.COMPOSTABLE_LOW).cookingFuel(ContextIntProviders.COOKING_TIME_DRY_PLANTS));
		BROWN_AUTUMNAL_LEAVES = TraverseRegistry.registerBlockItem("brown_autumnal_leaves", TraverseBlocks.BROWN_AUTUMNAL_LEAVES, new Item.Properties().compostable(ContextIntProviders.COMPOSTABLE_LOW));
		BROWN_AUTUMNAL_SAPLING = TraverseRegistry.registerBlockItem("brown_autumnal_sapling", TraverseBlocks.BROWN_AUTUMNAL_SAPLING, new Item.Properties().compostable(ContextIntProviders.COMPOSTABLE_LOW).cookingFuel(ContextIntProviders.COOKING_TIME_DRY_PLANTS));
		ORANGE_AUTUMNAL_LEAVES = TraverseRegistry.registerBlockItem("orange_autumnal_leaves", TraverseBlocks.ORANGE_AUTUMNAL_LEAVES, new Item.Properties().compostable(ContextIntProviders.COMPOSTABLE_LOW));
		ORANGE_AUTUMNAL_SAPLING = TraverseRegistry.registerBlockItem("orange_autumnal_sapling", TraverseBlocks.ORANGE_AUTUMNAL_SAPLING, new Item.Properties().compostable(ContextIntProviders.COMPOSTABLE_LOW).cookingFuel(ContextIntProviders.COOKING_TIME_DRY_PLANTS));
		YELLOW_AUTUMNAL_LEAVES = TraverseRegistry.registerBlockItem("yellow_autumnal_leaves", TraverseBlocks.YELLOW_AUTUMNAL_LEAVES, new Item.Properties().compostable(ContextIntProviders.COMPOSTABLE_LOW));
		YELLOW_AUTUMNAL_SAPLING = TraverseRegistry.registerBlockItem("yellow_autumnal_sapling", TraverseBlocks.YELLOW_AUTUMNAL_SAPLING, new Item.Properties().compostable(ContextIntProviders.COMPOSTABLE_LOW).cookingFuel(ContextIntProviders.COOKING_TIME_DRY_PLANTS));

		FIR_LEAVES = TraverseRegistry.registerBlockItem("fir_leaves", TraverseBlocks.FIR_LEAVES, new Item.Properties().compostable(ContextIntProviders.COMPOSTABLE_LOW));
		FIR_SAPLING = TraverseRegistry.registerBlockItem("fir_sapling", TraverseBlocks.FIR_SAPLING, new Item.Properties().compostable(ContextIntProviders.COMPOSTABLE_LOW).cookingFuel(ContextIntProviders.COOKING_TIME_DRY_PLANTS));

		FIR_LOG = TraverseRegistry.registerBlockItem("fir_log", TraverseBlocks.FIR_LOG, new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
		FIR_WOOD = TraverseRegistry.registerBlockItem("fir_wood", TraverseBlocks.FIR_WOOD, new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
		STRIPPED_FIR_LOG = TraverseRegistry.registerBlockItem("stripped_fir_log", TraverseBlocks.STRIPPED_FIR_LOG, new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
		STRIPPED_FIR_WOOD = TraverseRegistry.registerBlockItem("stripped_fir_wood", TraverseBlocks.STRIPPED_FIR_WOOD, new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
		FIR_PLANKS = TraverseRegistry.registerBlockItem("fir_planks", TraverseBlocks.FIR_PLANKS, new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
		FIR_SLAB = TraverseRegistry.registerBlockItem("fir_slab", TraverseBlocks.FIR_SLAB, new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_SLABS));
		FIR_PRESSURE_PLATE = TraverseRegistry.registerBlockItem("fir_pressure_plate", TraverseBlocks.FIR_PRESSURE_PLATE, new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
		FIR_FENCE = TraverseRegistry.registerBlockItem("fir_fence", TraverseBlocks.FIR_FENCE, new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
		FIR_TRAPDOOR = TraverseRegistry.registerBlockItem("fir_trapdoor", TraverseBlocks.FIR_TRAPDOOR, new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
		FIR_FENCE_GATE = TraverseRegistry.registerBlockItem("fir_fence_gate", TraverseBlocks.FIR_FENCE_GATE, new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
		FIR_STAIRS = TraverseRegistry.registerBlockItem("fir_stairs", TraverseBlocks.FIR_STAIRS, new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
		FIR_BUTTON = TraverseRegistry.registerBlockItem("fir_button", TraverseBlocks.FIR_BUTTON, new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_ITEMS_EXTRA_SMALL));
		FIR_DOOR = TraverseRegistry.register("fir_door", settings -> new DoubleHighBlockItem(TraverseBlocks.FIR_DOOR, settings), new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_ITEMS_LARGE).useBlockDescriptionPrefix());
		FIR_SHELF = TraverseRegistry.registerBlockItem("fir_shelf", TraverseBlocks.FIR_SHELF, new Item.Properties().component(DataComponents.CONTAINER, ItemContainerContents.EMPTY).cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));

		FIR_SIGN = TraverseRegistry.register("fir_sign", settings -> new StandingAndWallBlockItem(TraverseBlocks.FIR_SIGN, TraverseBlocks.FIR_WALL_SIGN, Direction.DOWN, settings), new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_ITEMS_LARGE).stacksTo(16).signText().useBlockDescriptionPrefix());
		FIR_HANGING_SIGN = TraverseRegistry.register("fir_hanging_sign", settings -> new HangingSignItem(TraverseBlocks.FIR_HANGING_SIGN, TraverseBlocks.FIR_WALL_HANGING_SIGN, settings), new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_HANGING_SIGNS).stacksTo(16).signText().useBlockDescriptionPrefix());
	}
}
