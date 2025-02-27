package com.mrbysco.enhancedfarming.handler;

import com.mrbysco.enhancedfarming.init.FarmingActions;
import com.mrbysco.enhancedfarming.init.FarmingTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.level.BlockEvent.BlockToolModificationEvent;

public class RakeHandler {
	@SubscribeEvent
	public void onToolUse(BlockToolModificationEvent event) {
		if (event.getItemAbility() == FarmingActions.RAKE_GATHER) {
			BlockState state = event.getState();
			if (state.is(FarmingTags.RAKE_BLOCKS)) {
				event.setFinalState(Blocks.DIRT.defaultBlockState());
			}
		}
	}
}
