package dev.cougar.core.staff.listener

import mx.fxmxgragfx.api.async.Async
import dev.cougar.core.Core
import dev.cougar.core.staff.packet.StaffJoinPacket
import dev.cougar.node.packet.handler.IncomingPacketHandler
import dev.cougar.node.packet.listener.PacketListener
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent


/**
 * Developed by FxMxGRAGFX
 * Project: Core
 **/

@Suppress("unused")
class StaffJoinListener : PacketListener, Listener {

    @EventHandler
    fun onStaffJoinBukkit(event : PlayerJoinEvent) {
        if(!event.player.hasPermission("staff.basic")) return
        mx.fxmxgragfx.api.async.Async {
            Core.getInstance().node.sendPacket(
                StaffJoinPacket(
                    event.player.uniqueId,
                    Bukkit.getServerName()
                )
            )
        }
    }

    @IncomingPacketHandler
    fun onStaffJoinPacket(packet : StaffJoinPacket) {
        Core.getOnlinePlayers().filter { p -> p.hasPermission("staff.basic") }.filter { p -> p.uniqueId != packet.player }.forEach { p ->
            run {
                p.sendMessage("")
            }
        }
    }

}