package com.mrbysco.enhancedfarming.init;

import com.mrbysco.enhancedfarming.EnhancedFarming;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;

public class FarmingLootTables {
	public static final ResourceKey<LootTable> GAMEPLAY_RAKE_DROPS = ResourceKey.create(Registries.LOOT_TABLE, new ResourceLocation(EnhancedFarming.MOD_ID, "gameplay/rake_drops"));
}
