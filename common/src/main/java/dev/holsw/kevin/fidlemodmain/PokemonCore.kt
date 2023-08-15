package dev.holsw.kevin.fidlemodmain

import com.cobblemon.mod.common.api.events.CobblemonEvents
import com.cobblemon.mod.common.api.pokemon.stats.Stats
import dev.holsw.kevin.fidlemodmain.commands.TutorCommand
import dev.architectury.event.events.common.CommandRegistrationEvent
import dev.holsw.kevin.fidlemodmain.commands.TutorCommand.registerDisplayPokemonCommand
import dev.holsw.kevin.fidlemodmain.commands.TutorCommand.registerReceivePokemonCommand
import dev.holsw.kevin.fidlemodmain.commands.TutorCommand.registerSavePokemonCommand
import dev.holsw.kevin.fidlemodmain.config.ConfigHandler
import net.minecraft.nbt.NbtCompound
import net.minecraft.text.Text
import org.apache.logging.log4j.LogManager

object PokemonCore {
    const val MOD_ID = "fidlemod"
    private val LOGGER = LogManager.getLogger()
    val config = ConfigHandler
    @JvmStatic
    fun init() {
        LOGGER.info("Fidlemod Enabled!")

        CommandRegistrationEvent.EVENT.register { dispatcher, _, _ ->
            registerSavePokemonCommand(dispatcher)
            registerDisplayPokemonCommand(dispatcher)
            registerReceivePokemonCommand(dispatcher)
        }

    }
}