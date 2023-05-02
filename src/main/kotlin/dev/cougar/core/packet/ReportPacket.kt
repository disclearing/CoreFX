package dev.cougar.core.packet

import com.google.gson.JsonObject
import dev.cougar.core.util.json.JsonChain
import dev.cougar.node.packet.Packet
import java.util.*


/**
 * Developed by FxMxGRAGFX
 * Project: Core
 **/

@Suppress("unused")
class ReportPacket : Packet {

    lateinit var reporter : UUID
    lateinit var reported : UUID
    lateinit var server : String
    lateinit var reason : String

    constructor()

    constructor(reporter : UUID, reported : UUID, server : String, reason : String) {
        this.reporter = reporter
        this.reported = reported
        this.server = server
        this.reason = reason
    }

    override fun deserialize(`object`: JsonObject) {
        reporter = UUID.fromString(`object`.get("reporter").asString)
        reported = UUID.fromString(`object`.get("reported").asString)
        server = `object`.get("server").asString
        reason = `object`.get("reason").asString
    }

    override fun id(): Int {
        return 4
    }

    override fun serialize(): JsonObject {
        return JsonChain()
            .addProperty("reporter", reporter.toString())
            .addProperty("reported", reported.toString())
            .addProperty("server", server)
            .addProperty("reason", reason)
            .get()
    }
}