package dev.cougar.core.command

import mx.fxmxgragfx.api.command.Command
import org.bukkit.entity.Player
import dev.cougar.core.util.string.CC

@Suppress("unused")
object MoreCommand {
    @JvmStatic
    @Command(names = ["more"], permission = "command.more")
    fun execute(player: Player) {
        if (player.itemInHand == null) {
            player.sendMessage(CC.RED.toString() + "There is nothing in your hand.")
            return
        }
        player.itemInHand.amount = 64
        player.updateInventory()
        player.sendMessage(CC.GREEN.toString() + "You gave yourself more of the item in your hand.")
    }
}