package dev.cougar.core.staff.command

import mx.fxmxgragfx.api.command.Command
import org.bukkit.entity.Player


/**
 * Developed by FxMxGRAGFX
 * Project: Core
 **/

object StaffCommand {

    @JvmStatic
    @Command(names = ["staff", "mod", "h", "staffmode"], permission = "staff.basic")
    fun execute(player : Player) {

    }
}