package com.holswilder.kevin.poketutormain.forge;

import dev.architectury.platform.forge.EventBuses;
import com.holswilder.kevin.poketutormain.PokeTutors;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(PokeTutors.MOD_ID)
public class PokeTutorsForge {
    public PokeTutorsForge() {
		// Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(PokeTutors.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
            PokeTutors.init();
    }
}