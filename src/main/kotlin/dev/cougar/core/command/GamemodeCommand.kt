package dev.cougar.core.command

import mx.fxmxgragfx.api.command.Command
import mx.fxmxgragfx.api.command.data.parameter.Param
import dev.cougar.core.util.string.CC
import org.bukkit.GameMode
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player


/**
 * Developed by FxMxGRAGFX
 * Project: Core
 **/

@Suppress("unused")
object GamemodeCommand {

    const val SELF = "command.gamemode"
    const val OTHER = "$SELF.other"

    @JvmStatic
    @Command(names = ["gamemode", "gm"], permission = SELF, description = "Set a player's gamemode", async = true)
    fun gamemode(sender: CommandSender, @Param(name = "mode", defaultValue = "-0*toggle*0-") mode: GameMode, @Param(name = "player", defaultValue = "self") target: Player) {
        run(sender, target, mode)
    }

    @JvmStatic
    @Command(names = ["gms", "gm0"], permission = SELF, description = "Set a player's gamemode to survival")
    fun gms(sender: CommandSender, @Param(name = "player", defaultValue = "self") target: Player) {
        run(sender, target, GameMode.SURVIVAL)
    }

    @JvmStatic
    @Command(names = ["gmc", "gm1"], permission = SELF, description = "Set a player's gamemode to creative")
    fun gmc(sender: CommandSender, @Param(name = "player", defaultValue = "self") target: Player) {
        run(sender, target, GameMode.CREATIVE)
    }

    @JvmStatic
    @Command(names = ["gma", "gm2"], permission = SELF, description = "Set a player's gamemode to adventure")
    fun gma(sender: CommandSender, @Param(name = "player", defaultValue = "self") target: Player) {
        run(sender, target, GameMode.ADVENTURE)
    }

    private fun run(sender: CommandSender, target: Player, mode: GameMode) {
        if (sender != target && !sender.hasPermission(OTHER)) {
            sender.sendMessage("${CC.RED}No permission to set other player's gamemode.")
            return
        }
        target.gameMode = mode
        if (sender != target) sender.sendMessage("${target.displayName} ${CC.GOLD}is now in ${CC.WHITE}$mode ${CC.GOLD}mode.")
        target.sendMessage("${CC.GOLD}You are now in ${CC.WHITE}$mode ${CC.GOLD}mode.")
    }
}