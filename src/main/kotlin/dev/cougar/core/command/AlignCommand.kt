package dev.cougar.core.command

import mx.fxmxgragfx.api.command.Command
import org.bukkit.entity.Player

@Suppress("unused")
object AlignCommand {

    @JvmStatic
    @Command(names = ["align"], permission = "command.align")
    fun execute(player: Player) {
        val location = player.location
        when (getYaw(player)) {
            Yaw.NORTH -> {
                location.yaw = 180f
            }
            Yaw.SOUTH -> {
                location.yaw = 0f
            }
            Yaw.EAST -> {
                location.yaw = -90f
            }
            else -> {
                location.yaw = 90f
            }
        }
        location.x = location.blockX + 0.5
        location.z = location.blockZ + 0.5
        location.pitch = 0f
        player.teleport(location)
    }

    private enum class Yaw {
        NORTH, EAST, WEST, SOUTH
    }

    private fun getYaw(player: Player): Yaw {
        var yaw = player.location.yaw
        yaw = (yaw % 360 + 360) % 360
        if (yaw > 135 && yaw <= 225) {
            return Yaw.NORTH
        } else if (yaw > 225 && yaw <= 310) {
            return Yaw.EAST
        } else if (yaw > 315 && yaw <= 360 || yaw in 0.0..45.0) {
            return Yaw.SOUTH
        }
        return Yaw.WEST
    }
}