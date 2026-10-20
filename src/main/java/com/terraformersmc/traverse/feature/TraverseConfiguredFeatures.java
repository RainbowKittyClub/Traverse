package com.terraformersmc.traverse.feature;

import com.google.common.collect.ImmutableList;
import com.terraformersmc.traverse.Traverse;
import com.terraformersmc.traverse.block.TraverseBlocks;
import com.terraformersmc.traverse.feature.placer.FallenTrunkPlacer;
import com.terraformersmc.traverse.feature.placer.NoneFoliagePlacer;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BlockStateProviders;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.data.worldgen.features.VegetationFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.TreePlacements;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.random.Weighted;
import net.minecraft.util.random.WeightedList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.RandomBooleanSelectorFeature;
import net.minecraft.world.level.levelgen.feature.SimpleBlockFeature;
import net.minecraft.world.level.levelgen.feature.SimpleRandomSelectorFeature;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.WeightedRandomSelectorFeature;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BushFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.SpruceFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.LeaveVineDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.PlaceOnGroundDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class TraverseConfiguredFeatures {
	private static final PlaceOnGroundDecorator LEAF_LITTER = new PlaceOnGroundDecorator(96, 4, 2, Holder.direct(new WeightedStateProvider(VegetationFeatures.leafLitterPatchBuilder(1, 3))));
	private static final PlaceOnGroundDecorator DENSE_LEAF_LITTER = new PlaceOnGroundDecorator(150, 2, 2, Holder.direct(new WeightedStateProvider(VegetationFeatures.leafLitterPatchBuilder(1, 4))));

	/* Logs and Shrubs */
	public static final ResourceKey<Feature> FALLEN_OAK_TREE = resourceKey("fallen_oak_tree");
	public static final ResourceKey<Feature> OAK_SHRUB = resourceKey("oak_shrub");
	public static final ResourceKey<Feature> WOODLANDS_TREES = resourceKey("woodlands_trees");

	/* Autumnal Trees */
	public static final ResourceKey<Feature> BROWN_AUTUMNAL_TREE = resourceKey("brown_autumnal_tree");
	public static final ResourceKey<Feature> ORANGE_AUTUMNAL_TREE = resourceKey("orange_autumnal_tree");
	public static final ResourceKey<Feature> RED_AUTUMNAL_TREE = resourceKey("red_autumnal_tree");
	public static final ResourceKey<Feature> YELLOW_AUTUMNAL_TREE = resourceKey("yellow_autumnal_tree");
	public static final ResourceKey<Feature> BROWN_AUTUMNAL_TREE_LEAF_LITTER = resourceKey("brown_autumnal_tree_leaf_litter");
	public static final ResourceKey<Feature> ORANGE_AUTUMNAL_TREE_LEAF_LITTER = resourceKey("orange_autumnal_tree_leaf_litter");
	public static final ResourceKey<Feature> RED_AUTUMNAL_TREE_LEAF_LITTER = resourceKey("red_autumnal_tree_leaf_litter");
	public static final ResourceKey<Feature> YELLOW_AUTUMNAL_TREE_LEAF_LITTER = resourceKey("yellow_autumnal_tree_leaf_litter");
	public static final ResourceKey<Feature> AUTUMNAL_TREES = resourceKey("autumnal_trees");

	/* Fir Trees */
	public static final ResourceKey<Feature> FIR_TREE = resourceKey("fir_tree");

	/* Swamp Trees */
	public static final ResourceKey<Feature> TALL_SWAMP_TREE = resourceKey("tall_swamp_tree");
	public static final ResourceKey<Feature> SWAMP_FUNGUS = resourceKey("swamp_fungus");

	/* Lush Vegetation */
	public static final ResourceKey<Feature> FLATLANDS_GRASS = resourceKey("flatlands_grass");
	public static final ResourceKey<Feature> LUSH_FLOWERS = resourceKey("lush_flowers");

	public static void bootstrap(BootstrapContext<Feature> context) {
		HolderGetter<Feature> configuredFeatures = context.lookup(Registries.FEATURE);
		HolderGetter<PlacedFeature> placedFeatures = context.lookup(Registries.PLACED_FEATURE);
		Holder<BlockStateProvider> belowTrunkProvider = context.lookup(Registries.BLOCK_STATE_PROVIDER).getOrThrow(BlockStateProviders.SOIL_BENEATH_TREE);

		/* Logs and Shrubs */
		context.register(FALLEN_OAK_TREE,
				fallenLog(
						Blocks.OAK_LOG,
						belowTrunkProvider
				)
				.ignoreVines()
				.build()
		);
		context.register(OAK_SHRUB,
				shrub(
						Blocks.OAK_LOG,
						Blocks.OAK_LEAVES,
						belowTrunkProvider
				)
				.ignoreVines()
				.build()
		);
		context.register(WOODLANDS_TREES,
				new WeightedRandomSelectorFeature(
						WeightedList.of(
								new Weighted<>(placedFeatures.getOrThrow(TraversePlacedFeatures.WOODLANDS_SHRUB), 1),
								new Weighted<>(placedFeatures.getOrThrow(TraversePlacedFeatures.WOODLANDS_FALLEN_LOG), 1),
								new Weighted<>(placedFeatures.getOrThrow(TreePlacements.OAK_LEAF_LITTER), 3)
						)
				)
		);

		/* Autumnal Trees */
		context.register(BROWN_AUTUMNAL_TREE,
				oakLike(
						Blocks.OAK_LOG,
						TraverseBlocks.BROWN_AUTUMNAL_LEAVES,
						4,
						belowTrunkProvider
				)
				.ignoreVines()
				.build()
		);
		context.register(ORANGE_AUTUMNAL_TREE,
				oakLike(
						Blocks.OAK_LOG,
						TraverseBlocks.ORANGE_AUTUMNAL_LEAVES,
						4,
						belowTrunkProvider
				)
			.ignoreVines()
			.build()
		);
		context.register(RED_AUTUMNAL_TREE,
				oakLike(
						Blocks.DARK_OAK_LOG,
						TraverseBlocks.RED_AUTUMNAL_LEAVES,
						4,
						belowTrunkProvider
				)
				.ignoreVines()
				.build()
		);
		context.register(YELLOW_AUTUMNAL_TREE,
				oakLike(
						Blocks.BIRCH_LOG,
						TraverseBlocks.YELLOW_AUTUMNAL_LEAVES,
						6,
						belowTrunkProvider
				)
				.ignoreVines()
				.build()
		);

		context.register(BROWN_AUTUMNAL_TREE_LEAF_LITTER,
				oakLike(
						Blocks.OAK_LOG,
						TraverseBlocks.BROWN_AUTUMNAL_LEAVES,
						4,
						belowTrunkProvider
				)
				.ignoreVines()
				.decorators(ImmutableList.of(LEAF_LITTER, DENSE_LEAF_LITTER))
				.build()
		);
		context.register(ORANGE_AUTUMNAL_TREE_LEAF_LITTER,
				oakLike(
						Blocks.OAK_LOG,
						TraverseBlocks.ORANGE_AUTUMNAL_LEAVES,
						4,
						belowTrunkProvider
				)
				.ignoreVines()
				.decorators(ImmutableList.of(LEAF_LITTER, DENSE_LEAF_LITTER))
				.build()
		);
		context.register(RED_AUTUMNAL_TREE_LEAF_LITTER,
				oakLike(
						Blocks.DARK_OAK_LOG,
						TraverseBlocks.RED_AUTUMNAL_LEAVES,
						4,
						belowTrunkProvider
				)
				.ignoreVines()
				.decorators(ImmutableList.of(LEAF_LITTER, DENSE_LEAF_LITTER))
				.build()
		);
		context.register(YELLOW_AUTUMNAL_TREE_LEAF_LITTER,
				oakLike(
						Blocks.BIRCH_LOG,
						TraverseBlocks.YELLOW_AUTUMNAL_LEAVES,
						6,
						belowTrunkProvider
				)
				.ignoreVines()
				.decorators(ImmutableList.of(LEAF_LITTER, DENSE_LEAF_LITTER))
				.build()
		);
		context.register(AUTUMNAL_TREES,
				new SimpleRandomSelectorFeature(
						HolderSet.direct(
								placedFeatures.getOrThrow(TraversePlacedFeatures.BROWN_AUTUMNAL_TREE_LEAF_LITTER),
								placedFeatures.getOrThrow(TraversePlacedFeatures.ORANGE_AUTUMNAL_TREE_LEAF_LITTER),
								placedFeatures.getOrThrow(TraversePlacedFeatures.RED_AUTUMNAL_TREE_LEAF_LITTER),
								placedFeatures.getOrThrow(TraversePlacedFeatures.YELLOW_AUTUMNAL_TREE_LEAF_LITTER),
								placedFeatures.getOrThrow(TreePlacements.OAK_LEAF_LITTER)
						)
				)
		);

		/* Fir Trees */
		context.register(FIR_TREE,
				new TreeFeature.Builder(
						BlockStateProvider.of(TraverseBlocks.FIR_LOG),
						new StraightTrunkPlacer(15, 15, 4),
						BlockStateProvider.of(TraverseBlocks.FIR_LEAVES),
						new SpruceFoliagePlacer(UniformInt.of(2, 3), UniformInt.of(1, 1), UniformInt.of(4, 12)),
						new TwoLayersFeatureSize(2, 0, 4),
						belowTrunkProvider
				)
				.ignoreVines()
				.build()
		);

		/* Swamp Trees */
		context.register(TALL_SWAMP_TREE,
				new TreeFeature.Builder(
						BlockStateProvider.of(Blocks.OAK_LOG),
						new StraightTrunkPlacer(7, 3, 0),
						BlockStateProvider.of(Blocks.OAK_LEAVES),
						new BlobFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0), 3),
						new TwoLayersFeatureSize(1, 0, 1),
						belowTrunkProvider
				)
				.decorators(ImmutableList.of(new LeaveVineDecorator(0.25f)))
				.build()
		);
		context.register(SWAMP_FUNGUS,
				new RandomBooleanSelectorFeature(
						PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(TreeFeatures.HUGE_BROWN_MUSHROOM)),
						PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(TreeFeatures.HUGE_RED_MUSHROOM))
				)
		);

		/* Lush Vegetation */
		context.register(FLATLANDS_GRASS,
				new SimpleBlockFeature(BlockStateProvider.of(Blocks.SHORT_GRASS))
		);
		context.register(LUSH_FLOWERS,
				new SimpleBlockFeature(new WeightedStateProvider(new WeightedList.Builder<BlockState>()
						.add(Blocks.POPPY.defaultBlockState(), 12)
						.add(Blocks.AZURE_BLUET.defaultBlockState(), 12)
						.add(Blocks.OXEYE_DAISY.defaultBlockState(), 12)
						.add(Blocks.DANDELION.defaultBlockState(), 8)
						.add(Blocks.ORANGE_TULIP.defaultBlockState(), 1)
						.add(Blocks.PINK_TULIP.defaultBlockState(), 1)
						.add(Blocks.RED_TULIP.defaultBlockState(), 1)
						.add(Blocks.WHITE_TULIP.defaultBlockState(), 1)
				))
		);
	}

	private static TreeFeature.Builder fallenLog(Block trunk, Holder<BlockStateProvider> belowTrunkProvider) {
		return new TreeFeature.Builder(
				BlockStateProvider.of(trunk),
				new FallenTrunkPlacer(3, 2, 0),
				BlockStateProvider.of(Blocks.OAK_LEAVES.defaultBlockState()),
				new NoneFoliagePlacer(),
				new TwoLayersFeatureSize(0, 0, 0),
				belowTrunkProvider
		);
	}

	private static TreeFeature.Builder shrub(Block trunk, Block leaves, Holder<BlockStateProvider> belowTrunkProvider) {
		return new TreeFeature.Builder(
				BlockStateProvider.of(trunk),
				new StraightTrunkPlacer(1, 0, 0),
				BlockStateProvider.of(leaves),
				new BushFoliagePlacer(ConstantInt.of(2), ConstantInt.of(1), 2),
				new TwoLayersFeatureSize(0, 0, 0),
				belowTrunkProvider
		);
	}

	private static TreeFeature.Builder oakLike(Block trunk, Block leaves, int height, Holder<BlockStateProvider> belowTrunkProvider) {
		return new TreeFeature.Builder(
				BlockStateProvider.of(trunk.defaultBlockState()),
				new StraightTrunkPlacer(height, 2, 0),
				BlockStateProvider.of(leaves.defaultBlockState()),
				new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
				new TwoLayersFeatureSize(1, 0, 1),
				belowTrunkProvider
		).ignoreVines();
	}

	private static ResourceKey<Feature> resourceKey(String path) {
		return ResourceKey.create(Registries.FEATURE, Identifier.fromNamespaceAndPath(Traverse.MOD_ID, path));
	}
}
