package dev.cougar.core.staff.command

import dev.cougar.core.Core
import dev.cougar.core.util.string.CC
import mx.fxmxgragfx.api.command.Command
import mx.fxmxgragfx.api.command.data.parameter.Param
import org.bukkit.entity.Player
import org.bukkit.metadata.FixedMetadataValue

/**
 * Developed by FxMxGRAGFX
 * Project: Core
 **/

@Suppress("unused")
object FreezeCommand {

    @JvmStatic
    @Command(names = ["freeze", "ss"], permission = "staff.basic")
    fun freeze(player : Player, @Param(name = "target")target : Player) {
        target.setMetadata("FROZEN", FixedMetadataValue(Core.getInstance(), true))
        player.sendMessage("${target.name} ${CC.GOLD}is now currently frozen")
    }
}