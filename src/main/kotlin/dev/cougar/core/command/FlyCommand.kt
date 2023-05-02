package dev.cougar.core.command

import mx.fxmxgragfx.api.command.Command
import mx.fxmxgragfx.api.command.data.parameter.Param
import dev.cougar.core.util.string.CC
import org.bukkit.entity.Player


/**
 * Developed by FxMxGRAGFX
 * Project: Core
 **/

@Suppress("unused")
object FlyCommand {

    const val SELF = "command.fly"
    const val OTHER = "$SELF.other"

    @JvmStatic
    @Command(names = ["fly", "flight"], permission = SELF, description = "Toggle a player's fly mode", async = true)
    fun fly(sender: Player, @Param(name = "player", defaultValue = "self") target: Player) {
        if(sender != target && !sender.hasPermission(OTHER)) {
            sender.sendMessage("${CC.RED}No permission to set other player's fly mode.")
            return
        }
        target.allowFlight = !target.allowFlight
        if(sender != target) sender.sendMessage("${target.displayName}${CC.GOLD}'s fly mode was set to ${CC.WHITE}${target.allowFlight}${CC.GOLD}.")
        target.sendMessage("${CC.GOLD}Your fly mode was set to ${CC.WHITE}${target.allowFlight}${CC.GOLD}.")
    }
}