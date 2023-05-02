package mx.fxmxgragfx.api.command.data.parameter.impl

import org.bukkit.entity.Player
import org.bukkit.command.CommandSender
import mx.fxmxgragfx.api.command.data.parameter.ParameterType
import org.bukkit.ChatColor
import java.util.*

class BooleanParameterType : ParameterType<Boolean?> {

    private val map: MutableMap<String, Boolean> = mutableMapOf()

    init {
        map["on"] = true
        map["yes"] = true
        map["false"] = false
        map["off"] = false
        map["no"] = false
    }

    override fun transform(sender: CommandSender, source: String): Boolean? {
        if (!this.map.containsKey(source.lowercase(Locale.getDefault()))) {
            sender.sendMessage("${ChatColor.RED} $source is not a valid boolean.")
            return null
        }

        return this.map[source.lowercase(Locale.getDefault())]
    }

    override fun tabComplete(player: Player, flags: Set<String>, source: String): List<String> {
        return ArrayList(this.map.keys)
    }

}