package dev.cougar.core.chat

import mx.fxmxgragfx.api.module.Module
import dev.cougar.core.Core
import dev.cougar.core.chat.command.ChatCommand


/**
 * Developed by FxMxGRAGFX
 * Project: Core
 **/

class ChatModule : Module {

    override fun init() {
        //Register Commands
        Core.getInstance().commandHandler.registerClass(ChatCommand::class.java)
        TODO("Not yet implemented")
    }

    override fun stop() {
        TODO("Not yet implemented")
    }
}