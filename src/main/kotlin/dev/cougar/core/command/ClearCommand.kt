package dev.cougar.core.command

import mx.fxmxgragfx.api.command.Command
import mx.fxmxgragfx.api.command.data.parameter.Param
import dev.cougar.core.util.string.CC
import org.bukkit.entity.Player

@Suppress("unused")
object ClearCommand {
    @JvmStatic
    @Command(names = ["clearinv", "clear", "ci"], permission = "command.clear")
    fun clear(player: Player, @Param(name = "target", defaultValue = "self") target: Player) {
        if (player !== target) {
            execute(player, target)
        } else {
            execute(player)
        }
    }

    fun execute(player: Player) {
        player.inventory.contents = arrayOfNulls(36)
        player.inventory.armorContents = arrayOfNulls(4)
        player.updateInventory()
        player.sendMessage(CC.GOLD.toString() + "You cleared your inventory.")
    }

    fun execute(player: Player, target: Player) {
        target.inventory.contents = arrayOfNulls(36)
        target.inventory.armorContents = arrayOfNulls(4)
        target.updateInventory()
        target.sendMessage(CC.GOLD.toString() + "Your inventory has been cleared by " + player.name)
        player.sendMessage(CC.GOLD.toString() + "You clear the inventory of " + target.name)
    }
}