package com.mrbysco.enhancedfarming.world.feature;

import com.google.common.collect.ImmutableList;
import com.mrbysco.enhancedfarming.EnhancedFarming;
import com.mrbysco.enhancedfarming.init.FarmingRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.BlockPredicateFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import net.minecraft.world.level.levelgen.placement.SurfaceWaterDepthFilter;

import java.util.List;

public class FarmingVegetationPlacements {
	public static final ResourceKey<PlacedFeature> PATCH_NETHER_FLOWER = createKey("patch_nether_flower");
	public static final ResourceKey<PlacedFeature> APPLE = createKey("apple");
	public static final ResourceKey<PlacedFeature> LEMON = createKey("lemon");
	public static final ResourceKey<PlacedFeature> ORANGE = createKey("orange");
	public static final ResourceKey<PlacedFeature> CHERRY = createKey("cherry");
	public static final ResourceKey<PlacedFeature> PEAR = createKey("pear");
	public static final ResourceKey<PlacedFeature> AVOCADO = createKey("avocado");
	public static final ResourceKey<PlacedFeature> MANGO = createKey("mango");
	public static final ResourceKey<PlacedFeature> BANANA = createKey("banana");
	public static final ResourceKey<PlacedFeature> OLIVE = createKey("olive");

	public static ResourceKey<PlacedFeature> createKey(String key) {
		return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(EnhancedFarming.MOD_ID, key));
	}

	public static void bootstrap(BootstrapContext<PlacedFeature> context) {
		HolderGetter<ConfiguredFeature<?, ?>> holdergetter = context.lookup(Registries.CONFIGURED_FEATURE);

		PlacementUtils.register(context, APPLE, holdergetter.getOrThrow(FarmingVegetation.APPLE_FRUIT_VEGETATION),
				fruitTreePlacement(4, PlacementUtils.countExtra(6, 0.1F, 1), FarmingRegistry.APPLE_SAPLING.get()));

		PlacementUtils.register(context, LEMON, holdergetter.getOrThrow(FarmingVegetation.LEMON_FRUIT_VEGETATION),
				fruitTreePlacement(4, PlacementUtils.countExtra(6, 0.1F, 1), FarmingRegistry.LEMON_SAPLING.get()));

		PlacementUtils.register(context, ORANGE, holdergetter.getOrThrow(FarmingVegetation.ORANGE_FRUIT_VEGETATION),
				fruitTreePlacement(4, PlacementUtils.countExtra(6, 0.1F, 1), FarmingRegistry.ORANGE_SAPLING.get()));

		PlacementUtils.register(context, CHERRY, holdergetter.getOrThrow(FarmingVegetation.CHERRY_FRUIT_VEGETATION),
				fruitTreePlacement(4, PlacementUtils.countExtra(6, 0.1F, 1), FarmingRegistry.CHERRY_SAPLING.get()));

		PlacementUtils.register(context, PEAR, holdergetter.getOrThrow(FarmingVegetation.PEAR_FRUIT_VEGETATION),
				fruitTreePlacement(4, PlacementUtils.countExtra(6, 0.1F, 1), FarmingRegistry.PEAR_SAPLING.get()));

		PlacementUtils.register(context, AVOCADO, holdergetter.getOrThrow(FarmingFeatureConfigs.AVOCADO),
				fruitTreePlacement(4, PlacementUtils.countExtra(6, 0.1F, 1), FarmingRegistry.AVOCADO_SAPLING.get()));

		PlacementUtils.register(context, MANGO, holdergetter.getOrThrow(FarmingFeatureConfigs.MANGO),
				fruitTreePlacement(4, PlacementUtils.countExtra(6, 0.1F, 1), FarmingRegistry.MANGO_SAPLING.get()));

		PlacementUtils.register(context, BANANA, holdergetter.getOrThrow(FarmingFeatureConfigs.BANANA),
				fruitTreePlacement(4, PlacementUtils.countExtra(6, 0.1F, 1), FarmingRegistry.BANANA_SAPLING.get()));

		PlacementUtils.register(context, OLIVE, holdergetter.getOrThrow(FarmingFeatureConfigs.OLIVE),
				fruitTreePlacement(4, PlacementUtils.countExtra(6, 0.1F, 1), FarmingRegistry.OLIVE_SAPLING.get()));

		List<PlacementModifier> list = List.of(CountPlacement.of(UniformInt.of(0, 5)), InSquarePlacement.spread(), PlacementUtils.RANGE_4_4, BiomeFilter.biome());
		PlacementUtils.register(context, PATCH_NETHER_FLOWER, holdergetter.getOrThrow(FarmingFeatureConfigs.PATCH_NETHER_FLOWER),
				list);
	}

	private static ImmutableList.Builder<PlacementModifier> treePlacementBase(PlacementModifier modifier) {
		return ImmutableList.<PlacementModifier>builder().add(modifier).add(InSquarePlacement.spread())
				.add(SurfaceWaterDepthFilter.forMaxDepth(0)).add(PlacementUtils.HEIGHTMAP_OCEAN_FLOOR).add(BiomeFilter.biome());
	}

	private static List<PlacementModifier> fruitTreePlacement(int rarity, PlacementModifier modifier, Block block) {
		return treePlacementBase(modifier).add(RarityFilter.onAverageOnceEvery(rarity))
				.add(BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(block.defaultBlockState(), BlockPos.ZERO)))
				.add(CountPlacement.of(1)).build();
	}
}
