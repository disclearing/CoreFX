package dev.cougar.core.staff.packet

import com.google.gson.JsonObject
import dev.cougar.core.util.json.JsonChain
import dev.cougar.node.packet.Packet
import java.util.*


/**
 * Developed by FxMxGRAGFX
 * Project: Core
 **/

class StaffChatPacket : Packet {

    lateinit var player : UUID
    lateinit var message : String
    lateinit var server : String

    constructor()

    constructor(player : UUID, message: String, server : String) {
        this.player = player
        this.message = message
        this.server = server
    }

    override fun deserialize(`object`: JsonObject) {
    }

    override fun id(): Int {
        return 1
    }

    override fun serialize(): JsonObject {
        return JsonChain()
            .addProperty("player", player.toString())
            .addProperty("message", message)
            .addProperty("server", server)
            .get()
    }
}