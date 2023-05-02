package dev.cougar.core.command

import mx.fxmxgragfx.api.command.Command
import org.bukkit.entity.Player
import dev.cougar.core.util.LocationUtil

object LocationCommand {
    @JvmStatic
    @Command(names = ["loc"], permission = "command.loc")
    fun execute(player: Player) {
        player.sendMessage(LocationUtil.serialize(player.location))
    }
}