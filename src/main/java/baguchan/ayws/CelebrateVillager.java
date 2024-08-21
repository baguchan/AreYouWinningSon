package baguchan.ayws;

import bagu_chan.bagus_lib.util.client.AnimationUtil;
import com.google.common.collect.ImmutableMap;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.behavior.Behavior;
import net.minecraft.world.entity.ai.behavior.CelebrateVillagersSurvivedRaid;
import net.minecraft.world.entity.ai.behavior.MoveToSkySeeingSpot;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.raid.Raid;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;
import java.util.Map;

public class CelebrateVillager extends Behavior<Villager>{
    @Nullable
    private Raid currentRaid;
    public CelebrateVillager() {
        super(ImmutableMap.of(), 600, 600);
    }

    protected boolean checkExtraStartConditions(ServerLevel p_22690_, Villager p_22691_) {
        BlockPos blockpos = p_22691_.blockPosition();
        this.currentRaid = p_22690_.getRaidAt(blockpos);
        return this.currentRaid != null && this.currentRaid.isVictory();
    }

    protected boolean canStillUse(ServerLevel p_22693_, Villager p_22694_, long p_22695_) {
        return this.currentRaid != null && !this.currentRaid.isStopped();
    }

    @Override
    protected void start(ServerLevel p_22540_, Villager p_22541_, long p_22542_) {
        AnimationUtil.sendAnimation(p_22541_, AYWSMod.WIN);
    }
    @Override
    protected void stop(ServerLevel p_22704_, Villager p_22705_, long p_22706_) {
        AnimationUtil.sendStopAnimation(p_22705_, AYWSMod.WIN);
    }

}
