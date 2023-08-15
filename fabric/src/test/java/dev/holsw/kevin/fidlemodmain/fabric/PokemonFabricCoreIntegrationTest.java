package dev.holsw.kevin.fidlemodmain.fabric;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

class PokemonFabricCoreIntegrationTest {
    @Test
    void onInitialize() {
        // arrange
        var sut = new PokemonFabricCore();

        // act
        Exception exception = null;
        try {
            sut.onInitialize();
        } catch(Exception e) {
            exception = e;
        }

        // assert
        assertNull(exception);
    }
}