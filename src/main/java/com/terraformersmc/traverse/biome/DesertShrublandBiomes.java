package com.terraformersmc.traverse.biome;

import com.terraformersmc.traverse.feature.TraversePlacedFeatures;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.attribute.BackgroundMusic;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.WorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import static com.terraformersmc.traverse.biome.TraverseBiomes.addBasicFeatures;

public class DesertShrublandBiomes {
	public static Biome create(BootstrapContext<Biome> registerable) {
		return new Biome.BiomeBuilder()
				.generationSettings(createGenerationSettings(registerable))
				.mobSpawnSettings(createSpawnSettings())
				.hasPrecipitation(false)
				.temperature(2.0F)
				.downfall(0.0F)
				.specialEffects(TraverseBiomes.createDefaultBiomeEffects()
						.grassColorOverride(0xBFB755)
						.foliageColorOverride(0xAEA42A)
						.build()
				)
				.putAttributes(TraverseBiomes.createDefaultEnvironmentAttributes()
						.set(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(SoundEvents.MUSIC_BIOME_DESERT))
						.set(EnvironmentAttributes.SNOW_GOLEM_MELTS, true)
						.build()
				)
				.build();
	}

	private static BiomeGenerationSettings createGenerationSettings(BootstrapContext<Biome> registerable) {
		HolderGetter<WorldCarver> configuredCarvers = registerable.lookup(Registries.CARVER);
		HolderGetter<PlacedFeature> placedFeatures = registerable.lookup(Registries.PLACED_FEATURE);

		BiomeGenerationSettings.Builder builder = new BiomeGenerationSettings.Builder(placedFeatures, configuredCarvers);
		BiomeDefaultFeatures.addFossilDecoration(builder);
		addBasicFeatures(builder);
		BiomeDefaultFeatures.addDefaultOres(builder);
		BiomeDefaultFeatures.addDefaultSoftDisks(builder);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, placedFeatures.getOrThrow(TraversePlacedFeatures.DESERT_SHRUBS));
		BiomeDefaultFeatures.addDefaultFlowers(builder);
		BiomeDefaultFeatures.addDefaultGrass(builder);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_DEAD_BUSH_2);
		BiomeDefaultFeatures.addDefaultMushrooms(builder);
		BiomeDefaultFeatures.addDesertExtraVegetation(builder);
		builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, placedFeatures.getOrThrow(TraversePlacedFeatures.DESERT_EXTRA_CACTUS));
		BiomeDefaultFeatures.addDesertExtraDecoration(builder);
		return builder.build();
	}

	private static MobSpawnSettings createSpawnSettings() {
		MobSpawnSettings.Builder builder = new net.minecraft.world.level.biome.MobSpawnSettings.Builder();
		TraverseBiomes.addDefaultCaveSpawnEntries(builder);
		builder.addSpawn(EntityTypes.SPIDER, 100, 4, 4);
		builder.addSpawn(EntityTypes.ZOMBIE, 38, 4, 4);
		builder.addSpawn(EntityTypes.ZOMBIE_VILLAGER, 2, 1, 1);
		builder.addSpawn(EntityTypes.HUSK, 60, 4, 4);
		builder.addSpawn(EntityTypes.SKELETON, 100, 4, 4);
		builder.addSpawn(EntityTypes.CREEPER, 100, 4, 4);
		builder.addSpawn(EntityTypes.SLIME, 100, 4, 4);
		builder.addSpawn(EntityTypes.ENDERMAN, 10, 1, 4);
		builder.addSpawn(EntityTypes.WITCH, 5, 1, 1);
		builder.addSpawn(EntityTypes.RABBIT, 4, 2, 3);
		builder.addSpawn(EntityTypes.ARMADILLO, 1, 1, 2);
		return builder.build();
	}
}
