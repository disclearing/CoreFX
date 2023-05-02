package dev.cougar.core.command

import mx.fxmxgragfx.api.command.Command
import mx.fxmxgragfx.api.command.data.parameter.Param
import dev.cougar.core.Core
import dev.cougar.core.util.BukkitReflection
import dev.cougar.core.util.string.CC
import org.bukkit.command.CommandSender

@Suppress("unused")
object SetSlotsCommand {
    @JvmStatic
    @Command(names = ["setslots"], async = true, permission = "command.setslots")
    fun execute(sender: CommandSender, @Param(name = "slots") slots: String) {
        BukkitReflection.setMaxPlayers(Core.getInstance().server, slots.toInt())
        sender.sendMessage(CC.GOLD.toString() + "You set the max slots to " + slots + ".")
    }
}