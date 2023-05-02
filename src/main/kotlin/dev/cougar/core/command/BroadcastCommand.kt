package dev.cougar.core.command

import mx.fxmxgragfx.api.command.Command
import mx.fxmxgragfx.api.command.data.parameter.Param
import org.bukkit.command.CommandSender
import org.bukkit.Bukkit
import dev.cougar.core.util.string.CC

@Suppress("unused", "UNUSED_PARAMETER")
object BroadcastCommand {
    @JvmStatic
    @Command(names = ["broadcast", "bc"], permission = "command.broadcast")
    fun execute(sender: CommandSender, @Param(name = "message", wildcard = true) broadcast: String) {
        val message = broadcast.replace("(&([a-f0-9l-or]))".toRegex(), "\u00A7$2")
        Bukkit.broadcastMessage(CC.translate(message))
    }
}