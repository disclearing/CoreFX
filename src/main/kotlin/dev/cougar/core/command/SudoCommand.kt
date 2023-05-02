package dev.cougar.core.command

import mx.fxmxgragfx.api.command.Command
import mx.fxmxgragfx.api.command.data.parameter.Param
import dev.cougar.core.Locale
import org.bukkit.ChatColor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object SudoCommand {
    @JvmStatic
    @Command(names = ["sudo"], permission = "command.sudo")
    fun execute(
        sender: CommandSender,
        @Param(name = "player") target: Player?,
        @Param(name = "command") command: String?
    ) {
        if (target == null) {
            sender.sendMessage(Locale.PLAYER_NOT_FOUND.format())
            return
        }
        target.performCommand(command)
        sender.sendMessage(ChatColor.GREEN.toString() + "Forced target to chat!")
    }
}