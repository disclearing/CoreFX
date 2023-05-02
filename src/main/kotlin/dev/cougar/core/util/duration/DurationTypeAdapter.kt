package dev.cougar.core.util.duration

import mx.fxmxgragfx.api.command.data.parameter.ParameterType
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

@Suppress("unused")
class DurationTypeAdapter : ParameterType<Any?> {
    override fun transform(sender: CommandSender, source: String): Duration {
        return Duration.fromString(source)
    }

    override fun tabComplete(player: Player, flags: Set<String>, source: String): List<String> {
        return listOf("")
    }
}