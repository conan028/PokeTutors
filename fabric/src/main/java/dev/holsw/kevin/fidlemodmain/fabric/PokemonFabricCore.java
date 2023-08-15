package dev.holsw.kevin.fidlemodmain.fabric;

import dev.holsw.kevin.fidlemodmain.PokemonCore;
import net.fabricmc.api.ModInitializer;

public class PokemonFabricCore implements ModInitializer {
    @Override
    public void onInitialize() {
        PokemonCore.init();
    }
}