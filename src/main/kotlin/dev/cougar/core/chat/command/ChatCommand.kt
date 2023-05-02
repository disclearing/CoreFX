package dev.cougar.core.chat.command

import mx.fxmxgragfx.api.command.Command
import mx.fxmxgragfx.api.command.data.parameter.Param
import org.bukkit.entity.Player


/**
 * Developed by FxMxGRAGFX
 * Project: Core
 **/

@Suppress("UNUSED_PARAMETER", "unused")
class ChatCommand {

    @Command(names = ["chat enable"], permission = "chat.enable", hidden = false)
    fun chatEnable(player : Player) {

    }

    @Command(names = ["chat disable"], permission = "chat.disable", hidden = false)
    fun chatDisable(player : Player) {

    }

    @Command(names = ["chat delay"], permission = "chat.delay", hidden = false)
    fun chatDelay(player : Player, @Param(name = "delay") delay : Int) {

    }
}