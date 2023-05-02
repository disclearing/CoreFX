package dev.cougar.core.command

import mx.fxmxgragfx.api.command.Command
import dev.cougar.core.Core
import org.bukkit.entity.Player

@Suppress("unused")
object ShowAllPlayersCommand {
    @JvmStatic
    @Command(names = ["showallplayers"], permission = "command.showallplayers")
    fun execute(player: Player) {
        for (otherPlayer in Core.getOnlinePlayers()) {
            player.showPlayer(otherPlayer)
        }
    }
}