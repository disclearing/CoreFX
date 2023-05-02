package dev.cougar.core.command

import mx.fxmxgragfx.api.command.Command
import mx.fxmxgragfx.api.command.data.parameter.Param
import org.bukkit.entity.Player

@Suppress("unused")
object InvModify {
    @JvmStatic
    @Command(names = ["invmodify"], permission = "command.invmodify")
    fun execute(player: Player, @Param(name = "target") target: Player) {
        player.openInventory(target.inventory)
    }
}