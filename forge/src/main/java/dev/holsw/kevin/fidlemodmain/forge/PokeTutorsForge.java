package dev.holsw.kevin.fidlemodmain.forge;

import dev.architectury.platform.forge.EventBuses;
import dev.holsw.kevin.fidlemodmain.PokemonCore;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(PokemonCore.MOD_ID)
public class PokeTutorsForge {
    public PokeTutorsForge() {
		// Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(PokemonCore.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
            PokemonCore.init();
    }
}