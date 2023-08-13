package dev.holsw.kevin.tutors;

import org.junit.jupiter.api.Test;

public class PokemonCoreIntegrationTest {
    @Test
    void initShouldNotThrowAnyExceptions() {
        PokemonCore.init();
    }
}
