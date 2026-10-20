package com.terraformersmc.traverse.surface.rules;

import com.terraformersmc.traverse.biome.TraverseBiomes;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.material.MaterialRules;
import net.minecraft.world.level.levelgen.material.condition.MaterialCondition;
import net.minecraft.world.level.levelgen.material.rule.MaterialRule;
import net.minecraft.world.level.levelgen.placement.CaveSurface;

import static net.minecraft.world.level.levelgen.material.MaterialRules.*;

public class TraverseSurfaceRules {
	private static final MaterialCondition ON_FLOOR = stoneDepthCheck(0, false, CaveSurface.FLOOR);
	private static final MaterialCondition UNDER_FLOOR = stoneDepthCheck(0, true, CaveSurface.FLOOR);

	private static MaterialRule block(Block block) {
		return MaterialRules.state(block.defaultBlockState());
	}

	public static MaterialRule bootstrap(RegistryAccess registryAccess) {
		HolderGetter<Biome> biomeGetter = registryAccess.lookupOrThrow(Registries.BIOME);

		// Sandy surface rules
		MaterialRule sandAndSandstone = sequence(ifTrue(UNDER_FLOOR,
			block(Blocks.SAND)), block(Blocks.SANDSTONE));
		MaterialRule redSandAndSandstone = sequence(ifTrue(UNDER_FLOOR,
			block(Blocks.RED_SAND)), block(Blocks.RED_SANDSTONE));

		// Biome-level rules
		MaterialRule desertShrubland = ifTrue(MaterialRules.isBiome(biomeGetter, TraverseBiomes.DESERT_SHRUBLAND),
			ifTrue(noiseCondition2d(Noises.BADLANDS_SURFACE, 0.01D), sandAndSandstone));
		MaterialRule lushSwamp = ifTrue(MaterialRules.isBiome(biomeGetter, TraverseBiomes.LUSH_SWAMP),
			ifTrue(ON_FLOOR,
				ifTrue(MaterialRules.yBlockCheck(VerticalAnchor.absolute(62), 0),
					ifTrue(MaterialRules.not(MaterialRules.yBlockCheck(VerticalAnchor.absolute(63), 0)),
						ifTrue(MaterialRules.noiseCondition2d(Noises.SWAMP, 0.0),
							block(Blocks.WATER))))));

		// Return a surface-only sequence of our surface rules
		return ifTrue(abovePreliminarySurface(),
				sequence(desertShrubland, lushSwamp));
	}
}
