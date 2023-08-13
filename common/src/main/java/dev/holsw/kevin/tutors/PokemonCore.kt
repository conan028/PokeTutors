package dev.holsw.kevin.tutors;

import com.cobblemon.mod.common.api.events.CobblemonEvents
import dev.holsw.kevin.tutors.commands.TutorCommand
import dev.architectury.event.events.common.CommandRegistrationEvent
import net.minecraft.text.Text
import org.apache.logging.log4j.LogManager

object PokemonCore {
    const val MOD_ID = "poketutors"
    val LOGGER = LogManager.getLogger()

    @JvmStatic
    fun init() {
        LOGGER.info("Poketutors Enabled!")



        CommandRegistrationEvent.EVENT.register { dispatcher, _, _ ->
            TutorCommand.registerTutorCommand(dispatcher)
        }


        CobblemonEvents.POKEMON_CAPTURED.subscribe { event ->
            val player = event.player
            val pokemon = event.pokemon.displayName.string

            player.sendMessage(Text.literal("you caught $pokemon"))
        }

    }
}