package dev.cougar.core.command

import mx.fxmxgragfx.api.command.Command
import mx.fxmxgragfx.api.command.data.parameter.Param
import dev.cougar.core.util.string.CC
import org.bukkit.entity.Player

@Suppress("unused")
object TPHereCommand {
    @JvmStatic
    @Command(names = ["tphere"], permission = "kore.tphere")
    fun execute(player: Player, @Param(name = "target") target: Player) {
        target.teleport(player)
        target.sendMessage(CC.GREEN.toString() + "You have been teleported to " + player.name)
        player.sendMessage(CC.GREEN.toString() + "You have teleported " + target.name + CC.GREEN.toString() + " to you")
    }
}