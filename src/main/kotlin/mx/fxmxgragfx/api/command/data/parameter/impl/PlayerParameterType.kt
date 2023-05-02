package mx.fxmxgragfx.api.command.data.parameter.impl

import mx.fxmxgragfx.api.command.data.parameter.ParameterType
import dev.cougar.core.Core
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class PlayerParameterType : ParameterType<Player?> {

    override fun transform(sender: CommandSender, source: String): Player? {
        if (sender is Player && (source.equals("self", true) || source.isEmpty())) {
            return sender
        }
        val player = Bukkit.getServer().getPlayer(source)
        if (player == null) {
            sender.sendMessage("${ChatColor.RED}Player not found.")
            return null
        }
        return player
    }

    override fun tabComplete(player: Player, flags: Set<String>, source: String): List<String> {
        val completions = ArrayList<String>()

        Core.getOnlinePlayers().stream()
                .forEach { target -> completions.add(target.name) }

        return completions
    }

}