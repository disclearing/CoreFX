package dev.cougar.core.command

import mx.fxmxgragfx.api.command.Command
import mx.fxmxgragfx.api.command.data.parameter.Param
import dev.cougar.core.util.BukkitReflection
import dev.cougar.core.util.string.CC
import dev.cougar.core.util.string.StyleUtil
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

@Suppress("unused")
object PingCommand {
    @JvmStatic
    @Command(names = ["ping"])
    fun ping(player: Player, @Param(name = "target", defaultValue = "self") target: Player) {
        if (player !== target) {
            execute(player, target)
        } else {
            execute(player)
        }
    }

    fun execute(player: Player) {
        player.sendMessage(CC.YELLOW.toString() + "Your Ping: " + StyleUtil.colorPing(BukkitReflection.getPing(player)))
    }

    fun execute(sender: CommandSender, target: Player?) {
        if (target == null) {
            sender.sendMessage(CC.RED.toString() + "A player with that name could not be found.")
        } else {
            sender.sendMessage(
                target.name + CC.YELLOW + "'s Ping: " +
                        StyleUtil.colorPing(BukkitReflection.getPing(target))
            )
        }
    }
}