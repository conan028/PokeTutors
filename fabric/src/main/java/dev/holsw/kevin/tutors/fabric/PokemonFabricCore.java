package dev.holsw.kevin.tutors.fabric;

import dev.holsw.kevin.tutors.PokemonCore;
import net.fabricmc.api.ModInitializer;
import dev.holsw.kevin.tutors.PokemonCore;

public class PokemonFabricCore implements ModInitializer {
    @Override
    public void onInitialize() {
        PokemonCore.init();
    }
}