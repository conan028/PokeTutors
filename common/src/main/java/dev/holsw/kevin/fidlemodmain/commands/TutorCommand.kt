package dev.holsw.kevin.fidlemodmain.commands

import com.cobblemon.mod.common.Cobblemon
import com.cobblemon.mod.common.item.PokemonItem
import com.cobblemon.mod.common.pokemon.Pokemon
import com.google.gson.JsonObject
import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.context.CommandContext
import net.minecraft.server.command.CommandManager.literal
import net.minecraft.server.command.ServerCommandSource
import dev.holsw.kevin.fidlemodmain.config.ConfigHandler.PokemonData
import dev.holsw.kevin.fidlemodmain.config.ConfigHandler.filePath
import dev.holsw.kevin.fidlemodmain.config.ConfigHandler.gson
import dev.holsw.kevin.fidlemodmain.screens.DisplayPokemonMenu
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

object TutorCommand {

    fun registerSavePokemonCommand(dispatcher: CommandDispatcher<ServerCommandSource>) {
        val command = literal("savepokemon")
            .executes(::savePokemonToData)
        dispatcher.register(command)
    }

    fun registerDisplayPokemonCommand(dispatcher: CommandDispatcher<ServerCommandSource>) {
        val command = literal("displaypokemon")
            .executes(::displayPokemon)
        dispatcher.register(command)
    }

    fun registerReceivePokemonCommand(dispatcher: CommandDispatcher<ServerCommandSource>) {
        val command = literal("getpokemon")
            .executes(::receivePokemonFromData)
        dispatcher.register(command)
    }


    private fun displayPokemon(ctx: CommandContext<ServerCommandSource>): Int {
        val player = ctx.source.player

        player?.openHandledScreen(DisplayPokemonMenu(player))

        return 1
    }


    private fun savePokemonToData(ctx: CommandContext<ServerCommandSource>): Int {
        val player = ctx.source.player
        val play = Cobblemon.storage.getParty(player!!.uuid)
        val playerParty = play.get(0)

        val json = JsonObject()
        val pokemonJson = playerParty?.saveToJSON(json)

        val tutsList : List<PokemonData?> = listOf(
            listOf(pokemonJson).toMutableList()?.let { PokemonData(player.uuid, player.displayName.string, it) }
        )

        val jsonData = gson.toJson(tutsList)

        if (playerParty != null) {
            play.remove(playerParty)
        }

        runBlocking {
            launch {
                filePath.writeText(jsonData)
            }
        }
        return 1
    }


    private fun receivePokemonFromData(ctx: CommandContext<ServerCommandSource>): Int {
        val player = ctx.source.player
        val playerParty = Cobblemon.storage.getParty(player!!.uuid)

        val currentData = filePath.readText()
        val getData = gson.fromJson(currentData, Array<PokemonData>::class.java).toMutableList()

        val findPlayer = getData.find { it.playerUUID == player.uuid }

        val pokemon: Pokemon? = findPlayer?.pokemonObject?.firstOrNull()?.let { Pokemon().loadFromJSON(it) }
        playerParty.add(pokemon!!)

        runBlocking {
            launch {
                findPlayer.pokemonObject.removeAt(0)
                val toJson = gson.toJson(getData)
                filePath.writeText(toJson)
            }
        }
        return 1
    }
}