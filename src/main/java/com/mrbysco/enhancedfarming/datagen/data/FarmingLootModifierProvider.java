package com.mrbysco.enhancedfarming.datagen.data;

import com.mrbysco.enhancedfarming.EnhancedFarming;
import com.mrbysco.enhancedfarming.lootmodifiers.GrassDropModifier;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;

import java.util.concurrent.CompletableFuture;

public class FarmingLootModifierProvider extends GlobalLootModifierProvider {
	public FarmingLootModifierProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
		super(packOutput, lookupProvider, EnhancedFarming.MOD_ID);
	}

	@Override
	protected void start() {
		this.add("grass_drops", new GrassDropModifier());
	}
}