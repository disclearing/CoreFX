package dev.cougar.core.command

import mx.fxmxgragfx.api.command.Command
import mx.fxmxgragfx.api.command.data.parameter.Param
import org.bukkit.entity.Player
import dev.cougar.core.util.item.ItemBuilder
import dev.cougar.core.util.string.CC

@Suppress("unused")
object SkullCommand {
    @JvmStatic
    @Command(names = ["skull"], permission = "command.skull")
    fun execute(player: Player, @Param(name = "SkullOwner") targetName: String) {
        player.inventory.addItem(ItemBuilder().skullOwner(targetName).build())
        player.sendMessage(CC.translate("&eYou have received the head of &f$targetName"))
    }
}