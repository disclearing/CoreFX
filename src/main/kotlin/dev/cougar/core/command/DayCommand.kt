package dev.cougar.core.command

import mx.fxmxgragfx.api.command.Command
import org.bukkit.entity.Player
import dev.cougar.core.util.string.CC

@Suppress("unused")
object DayCommand {
    @JvmStatic
    @Command(names = ["day"])
    fun execute(player: Player) {
        player.setPlayerTime(6000L, false)
        player.sendMessage(CC.GREEN.toString() + "It's now day time.")
    }
}