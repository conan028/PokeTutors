package dev.holsw.kevin.tutors.commands

import com.mojang.brigadier.Command
import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import com.mojang.brigadier.context.CommandContext
import net.minecraft.server.command.CommandManager
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.text.Text

class TutorCommand {

    companion object {

        fun registerTutorCommand(dispatcher: CommandDispatcher<ServerCommandSource>): Int {
            val command : LiteralArgumentBuilder<ServerCommandSource> = CommandManager.literal("works")
                .executes(Companion::thisWillWork)
            dispatcher.register(command)
            return Command.SINGLE_SUCCESS
        }


        private fun thisWillWork(ctx: CommandContext<ServerCommandSource>): Int {
            val player = ctx.source.player

            player?.sendMessage(Text.literal("hello"))


            return Command.SINGLE_SUCCESS
        }

    }

}