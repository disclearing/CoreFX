package dev.cougar.core.staff.listener

import dev.cougar.node.packet.listener.PacketListener
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent


/**
 * Developed by FxMxGRAGFX
 * Project: Core
 **/

@Suppress("unused")
class StaffChatListener : PacketListener, Listener {

    @EventHandler
    fun onStaffChat(event : AsyncPlayerChatEvent) {
        if(!event.player.hasPermission("staff.basic")) return
        if(!event.player.hasMetadata("STAFF_CHAT")) return
        //Thread { Core.getInstance().node.sendPacket(StaffChatPacket()) }
    }
}