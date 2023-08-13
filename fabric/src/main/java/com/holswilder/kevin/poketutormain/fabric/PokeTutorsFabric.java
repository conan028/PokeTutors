package com.holswilder.kevin.poketutormain.fabric;

import com.holswilder.kevin.poketutormain.PokeTutors;
import net.fabricmc.api.ModInitializer;

public class PokeTutorsFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        PokeTutors.init();
    }
}