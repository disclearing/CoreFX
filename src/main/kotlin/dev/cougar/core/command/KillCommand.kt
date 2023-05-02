package dev.cougar.core.command

import mx.fxmxgragfx.api.command.Command
import mx.fxmxgragfx.api.command.data.parameter.Param
import dev.cougar.core.util.player.PlayerUtil
import dev.cougar.core.util.string.CC
import org.bukkit.entity.Player
import kotlin.jvm.internal.Intrinsics


/**
 * Developed by FxMxGRAGFX
 * Project: Core
 **/

@Suppress("unused")
object KillCommand {

  @JvmStatic
  @Command(names = ["kill"], permission = "modsuite.admin", description = "Kill a player", async = true)
  fun kill(sender: Player, @Param(name = "player", defaultValue = "self") target: Player) {
    target.setHealth(0.0)
    if (Intrinsics.areEqual(target, sender)) {
      sender.sendMessage("${CC.GOLD}You have been killed.")
    } else {
      sender.sendMessage("${CC.RED}${target.displayName} ${CC.GOLD}has been killed.")
    }
  }
}