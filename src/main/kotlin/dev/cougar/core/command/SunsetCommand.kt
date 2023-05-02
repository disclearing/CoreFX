package dev.cougar.core.command

import mx.fxmxgragfx.api.command.Command
import dev.cougar.core.util.string.CC
import org.bukkit.entity.Player

@Suppress("unused")
object SunsetCommand {
    @JvmStatic
    @Command(names = ["sunset", "evening"])
    fun execute(player: Player) {
        player.setPlayerTime(12000, false)
        player.sendMessage(CC.GREEN.toString() + "It's now sunset.")
    }
}