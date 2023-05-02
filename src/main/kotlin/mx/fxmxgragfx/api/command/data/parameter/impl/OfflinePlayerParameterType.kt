package mx.fxmxgragfx.api.command.data.parameter.impl

import mx.fxmxgragfx.api.command.data.parameter.ParameterType
import dev.cougar.core.Core
import org.bukkit.Bukkit
import org.bukkit.OfflinePlayer
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

@Suppress("DEPRECATION", "NAME_SHADOWING")
class OfflinePlayerParameterType : ParameterType<OfflinePlayer> {

    override fun transform(sender: CommandSender, source: String): OfflinePlayer {
        return if (sender is Player && (source.equals("self", ignoreCase = true) || source == "")) {
            sender
        } else Bukkit.getServer().getOfflinePlayer(source)
    }

    override fun tabComplete(player: Player, flags: Set<String>, source: String): List<String> {
        val completions = ArrayList<String>()

        for (player in Core.getOnlinePlayers()) {
            completions.add(player.name)
        }

        return completions
    }

}