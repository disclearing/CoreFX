package mx.fxmxgragfx.api.command.data.parameter.impl

import mx.fxmxgragfx.api.command.data.parameter.ParameterType
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.command.CommandSender

class IntegerParameterType : ParameterType<Int?> {

    override fun transform(sender: CommandSender, source: String): Int? {
        return try {
            Integer.parseInt(source)
        } catch (exception: NumberFormatException) {
            sender.sendMessage("${ChatColor.RED} $source is not a valid number.")
            null
        }
    }

    override fun tabComplete(player: Player, flags: Set<String>, source: String): List<String> {
        return listOf()
    }

}