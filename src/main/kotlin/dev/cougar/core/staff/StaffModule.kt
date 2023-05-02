package dev.cougar.core.staff

import mx.fxmxgragfx.api.classes.ClassUtils
import mx.fxmxgragfx.api.handler.Handler
import mx.fxmxgragfx.api.module.Module
import dev.cougar.core.Core
import dev.cougar.node.packet.Packet
import dev.cougar.node.packet.listener.PacketListener
import org.bukkit.event.Listener


/**
 * Developed by FxMxGRAGFX
 * Project: Core
 **/

class StaffModule : Module {

    override fun init() {
        //Listener Packets
        ClassUtils.getClassesInPackage(Core.getInstance(), "dev.cougar.core.staff.listener", PacketListener::class.java).forEach(
            Core.getInstance().node::registerListenerClass)
        //Listener Bukkit
        ClassUtils.getClassesInPackage(Core.getInstance(), "dev.cougar.core.staff.listener", Listener::class.java).forEach{ c -> Handler(c)}
        //Register Packets
        ClassUtils.getClassesInPackage(Core.getInstance(), "dev.cougar.core.staff.packet", Packet::class.java).forEach(
            Core.getInstance().node::registerPacket)
    }

    override fun stop() {
        TODO("Not yet implemented")
    }
}