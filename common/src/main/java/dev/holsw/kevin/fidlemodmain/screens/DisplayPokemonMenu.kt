package dev.holsw.kevin.fidlemodmain.screens

import com.cobblemon.mod.common.item.PokemonItem
import com.cobblemon.mod.common.pokemon.Pokemon
import dev.holsw.kevin.fidlemodmain.config.ConfigHandler.filePath
import dev.holsw.kevin.fidlemodmain.config.ConfigHandler.gson
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.SimpleInventory
import net.minecraft.screen.*
import net.minecraft.screen.slot.SlotActionType
import net.minecraft.text.Text
import dev.holsw.kevin.fidlemodmain.config.ConfigHandler.PokemonData

class DisplayPokemonMenu (player: PlayerEntity) : NamedScreenHandlerFactory {

    private val inventory: SimpleInventory

    init {
        inventory = SimpleInventory(size())

        val currentData = filePath.readText()
        val getData = gson.fromJson(currentData, Array<PokemonData>::class.java).toMutableList()

        val findPlayer = getData.find { it.playerUUID == player.uuid }

        val pokemon : Pokemon? = findPlayer?.pokemonObject?.firstOrNull()?.let { Pokemon().loadFromJSON(it) }

        inventory.setStack(13, pokemon?.let { PokemonItem.Companion.from(it) })
    }
    override fun createMenu(syncId: Int, inv: PlayerInventory, player: PlayerEntity): ScreenHandler {
        val handler = object : GenericContainerScreenHandler(
            ScreenHandlerType.GENERIC_9X3, syncId, inv, inventory, rows()
        ) {
            override fun onSlotClick(slotIndex: Int, button: Int, actionType: SlotActionType?, player: PlayerEntity?) {

            }
        }
        return handler
    }

    fun size(): Int {
        return rows() * 9
    }

    fun rows(): Int {
        return 3
    }

    override fun getDisplayName(): Text {
        return Text.literal("<gold>Pokemon Display")
    }
}