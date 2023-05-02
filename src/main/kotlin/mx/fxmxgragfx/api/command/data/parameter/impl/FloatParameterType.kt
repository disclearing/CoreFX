package mx.fxmxgragfx.api.command.data.parameter.impl

import mx.fxmxgragfx.api.command.data.parameter.ParameterType
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.command.CommandSender
import java.util.*

class FloatParameterType : ParameterType<Float?> {

    override fun transform(sender: CommandSender, source: String): Float? {
        if (source.lowercase(Locale.getDefault()).contains("e")) {
            sender.sendMessage(ChatColor.RED.toString() + source + " is not a valid number.")
            return null
        }

        try {
            val parsed = java.lang.Float.parseFloat(source)

            if (java.lang.Float.isNaN(parsed) || !java.lang.Float.isFinite(parsed)) {
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