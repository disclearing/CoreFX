package dev.cougar.core.command

import mx.fxmxgragfx.api.command.Command
import mx.fxmxgragfx.api.command.data.parameter.Param
import dev.cougar.core.util.string.CC
import org.bukkit.entity.Player

@Suppress("unused")
object RenameCommand {
    @JvmStatic
    @Command(names = ["rename"], permission = "command.rename")
    fun execute(player: Player, @Param(name = "name", wildcard = true) name: String?) {
        if (player.itemInHand != null) {
            val itemStack = player.itemInHand
            val itemMeta = itemStack.itemMeta
            itemMeta.displayName = name?.let { CC.translate(it) }
            itemStack.itemMeta = itemMeta
            player.updateInventory()
            player.sendMessage(CC.GREEN.toString() + "You renamed the item in your hand.")
        } else {
            player.sendMessage(CC.RED.toString() + "There is nothing in your hand.")
        }
    }
}