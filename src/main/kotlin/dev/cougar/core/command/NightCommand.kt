package dev.cougar.core.command

import mx.fxmxgragfx.api.command.Command
import org.bukkit.entity.Player
import dev.cougar.core.util.string.CC

@Suppress("unused")
object NightCommand {
    @JvmStatic
    @Command(names = ["night"])
    fun execute(player: Player) {
        player.setPlayerTime(18000L, false)
        player.sendMessage(CC.GREEN.toString() + "It's now night time.")
    }
}