package dev.holsw.kevin.tutors;

import com.cobblemon.mod.common.api.events.CobblemonEvents
import com.cobblemon.mod.common.api.pokemon.stats.Stats
import dev.holsw.kevin.tutors.commands.TutorCommand
import dev.architectury.event.events.common.CommandRegistrationEvent
import net.minecraft.nbt.NbtCompound
import net.minecraft.text.Text
import org.apache.logging.log4j.LogManager
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

object PokemonCore {
    const val MOD_ID = "poketutors"
    val LOGGER = LogManager.getLogger()

    val hostname = "localhost"
    val port = "3306"
    val username = "root"
    val password = "pass"
    val databaseName = "testServer"


    init {
        val jdbcUrl = "jdbc:mysql://$hostname:$port/$databaseName"
        var connection: Connection? = null

        try {
            Class.forName("com.mysql.cj.jdbc.Driver")

            connection = DriverManager.getConnection(jdbcUrl, username, password)

            println("Connected to the local database")

        } catch (e: SQLException) {
            e.printStackTrace()
            println("Database connection error")

        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
            println("JDBC driver not found")
        } finally {
            connection?.close()
        }
    }

    @JvmStatic
    fun init() {
        LOGGER.info("Poketutors Enabled!")

        CommandRegistrationEvent.EVENT.register { dispatcher, _, _ ->
            TutorCommand.registerTutorCommand(dispatcher)
        }


        CobblemonEvents.BATTLE_VICTORY.subscribe { event ->
            event.battle.actors.forEach { actor ->
                actor.pokemonList.getOrNull(0)?.originalPokemon?.let { pokemon ->
                    if (pokemon.isPlayerOwned()) {
                        if (event.battle.isPvW) {
                            event.winners.getOrNull(0)?.battle?.players?.forEach { player ->
                                player.sendMessage(Text.literal(pokemon.displayName.string))
                                val fullNbt: NbtCompound? = pokemon.heldItem().nbt
                                when (fullNbt?.getString("breedItem")) {
                                    "Power Weight" -> pokemon.evs[Stats.HP] = pokemon.evs[Stats.HP]?.plus(4) ?: 4
                                    "Power Brace" -> pokemon.evs[Stats.ATTACK] = pokemon.evs[Stats.ATTACK]?.plus(4) ?: 4
                                    "Power Belt" -> pokemon.evs[Stats.DEFENCE] =
                                        pokemon.evs[Stats.DEFENCE]?.plus(4) ?: 4

                                    "Power Lens" -> pokemon.evs[Stats.SPECIAL_ATTACK] =
                                        pokemon.evs[Stats.SPECIAL_ATTACK]?.plus(4) ?: 4

                                    "Power Band" -> pokemon.evs[Stats.SPECIAL_DEFENCE] =
                                        pokemon.evs[Stats.SPECIAL_DEFENCE]?.plus(4) ?: 4

                                    "Power Anklet" -> pokemon.evs[Stats.SPEED] = pokemon.evs[Stats.SPEED]?.plus(4) ?: 4
                                }
                            }
                        } else {
                            return@let
                        }
                    }
                }
            }
        }
    }
}