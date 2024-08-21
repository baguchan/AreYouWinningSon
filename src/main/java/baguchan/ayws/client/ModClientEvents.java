package baguchan.ayws.client;

import bagu_chan.bagus_lib.animation.BaguAnimationController;
import bagu_chan.bagus_lib.api.client.IRootModel;
import bagu_chan.bagus_lib.client.event.BagusModelEvent;
import bagu_chan.bagus_lib.util.client.AnimationUtil;
import baguchan.ayws.AYWSMod;
import baguchan.ayws.client.animation.VillagerAnimation;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.VillagerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ScreenEvent;

import java.util.Optional;

@EventBusSubscriber(modid = AYWSMod.MODID, value = Dist.CLIENT)
public class ModClientEvents {

    @SubscribeEvent
    public static void onInitModelEvent(BagusModelEvent.Init event) {
        IRootModel rootModel = event.getRootModel();
        if (rootModel != null) {
            rootModel.getBagusRoot().getAllParts().forEach(ModelPart::resetPose);
        }
    }

    @SubscribeEvent
    public static void onAnimateModelEvent(BagusModelEvent.PostAnimate event) {
        IRootModel rootModel = event.getRootModel();
        BaguAnimationController controller = AnimationUtil.getAnimationController(event.getEntity());
        if (rootModel != null && controller != null) {
            if (controller.getAnimationState(AYWSMod.WIN).isStarted()) {
                if (event.getModel() instanceof VillagerModel<?> humanoidModel) {
                    Optional<ModelPart> right_leg = humanoidModel.getAnyDescendantWithName("right_leg");
                    Optional<ModelPart> left_leg = humanoidModel.getAnyDescendantWithName("left_leg");
                    if (right_leg.isPresent() && left_leg.isPresent()) {
                        right_leg.get().xRot = 0.0F;
                        left_leg.get().xRot = 0.0F;
                    }
                }
            }
            rootModel.animateBagu(controller.getAnimationState(AYWSMod.WIN), VillagerAnimation.dance, event.getAgeInTick());
            rootModel.animateBagu(controller.getAnimationState(AYWSMod.WIN), VillagerAnimation.dance_rotate, event.getAgeInTick());
        }
    }
}