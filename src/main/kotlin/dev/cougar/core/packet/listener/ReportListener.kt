package dev.cougar.core.packet.listener

import dev.cougar.core.Core
import dev.cougar.core.packet.ReportPacket
import dev.cougar.node.packet.handler.IncomingPacketHandler
import dev.cougar.node.packet.listener.PacketListener


/**
 * Developed by FxMxGRAGFX
 * Project: Core
 **/

class ReportListener : PacketListener {

    @IncomingPacketHandler
    fun onPlayerReported(packet : ReportPacket) {
        Core.getOnlinePlayers().filter { p -> p.hasPermission("staff.basic") }.forEach { p ->
            run {
                p.sendMessage(packet.serialize().toString())
            }
        }
    }
}