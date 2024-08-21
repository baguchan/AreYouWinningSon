package baguchan.ayws.mixin;

import baguchan.ayws.CelebrateVillager;
import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.world.entity.ai.behavior.Behavior;
import net.minecraft.world.entity.ai.behavior.VillagerGoalPackages;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerProfession;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;

@Mixin(VillagerGoalPackages.class)
public class VillagerGoalPackagesMixin {
    @Inject(method = "getRaidPackage", at = @At("RETURN"), cancellable = true)
    private static void getRaidPackage(VillagerProfession profession, float p_24591_, CallbackInfoReturnable<ImmutableList<Pair<Integer, ? extends Behavior<? super Villager>>>> ci) {
        List<Pair<Integer, ? extends Behavior<? super Villager>>> copy = new ArrayList<>(ci.getReturnValue());

        copy.add(Pair.of(0, new CelebrateVillager()));

        ci.setReturnValue(ImmutableList.copyOf(copy));;
    }
}
