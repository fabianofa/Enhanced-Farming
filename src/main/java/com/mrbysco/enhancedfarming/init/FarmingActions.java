package com.mrbysco.enhancedfarming.init;

import com.google.common.collect.Sets;
import net.neoforged.neoforge.common.ItemAbility;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FarmingActions {
	public static final ItemAbility RAKE_GATHER = ItemAbility.get("rake_gather");
	public static final Set<ItemAbility> DEFAULT_RAKE_ACTIONS = Stream.of(RAKE_GATHER).collect(Collectors.toCollection(Sets::newIdentityHashSet));
}
