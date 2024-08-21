package baguchan.ayws;

import bagu_chan.bagus_lib.event.RegisterBagusAnimationEvents;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(AYWSMod.MODID)
public class AYWSMod
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "ayws";

    public static final ResourceLocation WIN = ResourceLocation.fromNamespaceAndPath(MODID, "win");


    public AYWSMod(IEventBus modEventBus, ModContainer modContainer)
    {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);
        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (ExampleMod) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.addListener(this::registerAnimation);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
    }

    public void registerAnimation(RegisterBagusAnimationEvents events) {
        events.addAnimationState(WIN);
    }
}
