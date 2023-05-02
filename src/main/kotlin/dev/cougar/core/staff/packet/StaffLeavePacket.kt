package dev.cougar.core.staff.packet

import com.google.gson.JsonObject
import dev.cougar.core.util.json.JsonChain
import dev.cougar.node.packet.Packet
import java.util.*


/**
 * Developed by FxMxGRAGFX
 * Project: Core
 **/

class StaffLeavePacket : Packet {

    lateinit var player : UUID
    lateinit var server : String

    constructor()

    constructor(player : UUID, server : String) {
        this.player = player
        this.server = server
    }

    override fun deserialize(`object`: JsonObject) {
        player = UUID.fromString(`object`.get("player").asString)
        server = `object`.get("server").asString
    }

    override fun id(): Int {
        return 3
    }

    override fun serialize(): JsonObject {
        return JsonChain()
            .addProperty("player", player.toString())
            .addProperty("server", server)
            .get()
    }
}