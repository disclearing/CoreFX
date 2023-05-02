package dev.cougar.core.command

import mx.fxmxgragfx.api.command.Command
import mx.fxmxgragfx.api.command.data.parameter.Param
import dev.cougar.core.Locale
import org.bukkit.entity.Player
import dev.cougar.core.util.string.CC

@Suppress("unused")
object HealCommand {
    @JvmStatic
    @Command(names = ["heal"], permission = "command.heal")
    fun heal(player: Player, @Param(name = "target", defaultValue = "self") target: Player) {
        if (player !== target) {
            execute(player, target)
        } else {
            execute(player)
        }
    }

    fun execute(player: Player) {
        player.setHealth(20.0)
        player.foodLevel = 20
        player.saturation = 5.0f
        player.updateInventory()
        player.sendMessage(CC.GOLD.toString() + "You healed yourself.")
    }

    fun execute(sender: Player, player: Player?) {
        if (player == null) {
            sender.sendMessage(Locale.PLAYER_NOT_FOUND.format())
            return
        }
        player.setHealth(20.0)
        player.foodLevel = 20
        player.saturation = 5.0f
        player.updateInventory()
        player.sendMessage(CC.GOLD.toString() + "You have been healed by " + sender.name)
    }
}