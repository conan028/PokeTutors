package dev.holsw.kevin.fidlemodmain.config

import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import java.io.File
import java.util.*

object ConfigHandler {

    val filePath = File("config/FidleMod/data.json")

    val gson = GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create()

    init {
        if (filePath.exists()) {
            filePath.mkdirs()
        }
    }

    data class PokemonData(
        val playerUUID: UUID,
        val playerName: String,
        val pokemonObject: MutableList<JsonObject?> = mutableListOf()
    )

}