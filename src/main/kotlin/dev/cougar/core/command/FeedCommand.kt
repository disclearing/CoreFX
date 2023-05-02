package dev.cougar.core.command

import mx.fxmxgragfx.api.command.Command
import mx.fxmxgragfx.api.command.data.parameter.Param
import dev.cougar.core.Locale
import org.bukkit.entity.Player
import dev.cougar.core.util.string.CC

@Suppress("unused")
object FeedCommand {
    @JvmStatic
    @Command(names = ["feed", "eat"], permission = "command.feed")
    fun eat(player: Player, @Param(name = "target", defaultValue = "self") target: Player) {
        if (player !== target) {
            execute(player, target)
        } else {
            execute(player)
        }
    }

    fun execute(player: Player) {
        player.foodLevel = 20
        player.saturation = 5.0f
        player.updateInventory()
        player.sendMessage(CC.GOLD.toString() + "You feed yourself.")
    }

    fun execute(player: Player, target: Player?) {
        if (target == null) {
            player.sendMessage(Locale.PLAYER_NOT_FOUND.format())
            return
        }
        target.foodLevel = 20
        target.saturation = 5.0f
        target.updateInventory()
        target.sendMessage(CC.GOLD.toString() + "You have been fed by " + player.name)
    }
}