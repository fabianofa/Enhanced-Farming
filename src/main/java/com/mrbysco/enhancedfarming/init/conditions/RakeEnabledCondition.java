package com.mrbysco.enhancedfarming.init.conditions;

import com.mojang.serialization.MapCodec;
import com.mrbysco.enhancedfarming.config.FarmingConfig;
import net.neoforged.neoforge.common.conditions.ICondition;

public class RakeEnabledCondition implements ICondition {
	public static final RakeEnabledCondition INSTANCE = new RakeEnabledCondition();

	public static final MapCodec<RakeEnabledCondition> CODEC = MapCodec.unit(INSTANCE).stable();

	private RakeEnabledCondition() {
	}

	@Override
	public boolean test(IContext context) {
		return FarmingConfig.COMMON.cropToSeeds.get();
	}

	@Override
	public MapCodec<? extends ICondition> codec() {
		return CODEC;
	}

	@Override
	public String toString() {
		return "rake_enabled";
	}
}