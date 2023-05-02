package dev.cougar.core.command

import mx.fxmxgragfx.api.command.Command
import mx.fxmxgragfx.api.command.data.parameter.Param
import org.bukkit.entity.Player

@Suppress("unused")
object InvseeCommand {
    @JvmStatic
    @Command(names = ["invsee"], permission = "core.invsee")
    fun execute(player: Player, @Param(name = "target") target: Player) {
    }
}