package dev.cougar.core.staff.listener

import dev.cougar.node.packet.listener.PacketListener
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerQuitEvent


/**
 * Developed by FxMxGRAGFX
 * Project: Core
 **/

@Suppress("unused")
class StaffLeaveListener : PacketListener, Listener {

    @EventHandler
    fun onStaffLeave(event : PlayerQuitEvent) {
        if(!event.player.hasPermission("staff.basic")) return
    }
}