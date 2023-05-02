package dev.cougar.core.command

import mx.fxmxgragfx.api.command.Command
import mx.fxmxgragfx.api.command.data.parameter.Param
import org.bukkit.entity.Player
import dev.cougar.core.util.string.CC

@Suppress("unused")
object TeleportToPlayerCommand {
    @JvmStatic
    @Command(names = ["tp"], permission = "core.staff")
    fun execute(player: Player, @Param(name = "target") target: Player?) {
        if (target == null) {
            player.sendMessage("Player not found")
            return
        }
        player.teleport(target)
        player.sendMessage(CC.GREEN.toString() + "You have been teleported to " + CC.RESET + target.displayName)
    }
}