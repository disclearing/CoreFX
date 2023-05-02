package mx.fxmxgragfx.api.command.data.parameter.impl

import mx.fxmxgragfx.api.command.data.parameter.ParameterType
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.command.CommandSender
import java.util.*

class DoubleParameterType : ParameterType<Double?> {

    override fun transform(sender: CommandSender, source: String): Double? {
        if (source.lowercase(Locale.getDefault()).contains("e")) {
            sender.sendMessage(ChatColor.RED.toString() + source + " is not a valid number.")
            return null
        }

        try {
            val parsed = java.lang.Double.parseDouble(source)

            if (java.lang.Double.isNaN(parsed) || !java.lang.Double.isFinite(parsed)) {
                sender.sendMessage(ChatColor.RED.toString() + source + " is not a valid number.")
                return null
            }

            return parsed
        } catch (exception: NumberFormatException) {
            sender.sendMessage(ChatColor.RED.toString() + source + " is not a valid number.")
            return null
        }

    }

    override fun tabComplete(player: Player, flags: Set<String>, source: String): List<String> {
        return listOf()
    }

}