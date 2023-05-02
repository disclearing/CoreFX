package dev.cougar.core.command

import mx.fxmxgragfx.api.command.Command
import org.bukkit.entity.Player
import dev.cougar.core.Core
import dev.cougar.core.util.string.CC

@Suppress("unused")
object HelpCommand {
    @JvmStatic
    @Command(names = ["help", "ts", "discord", "?", "store", "links", "twitter"], async = true)
    fun execute(player: Player) {
        for (message in Core.getInstance().mainConfig.getStringList("SETTINGS.HELP_MESSAGE")) {
            player.sendMessage(CC.translate(message))
        }
    }
}