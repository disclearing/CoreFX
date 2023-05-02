package dev.cougar.core.command

import mx.fxmxgragfx.api.command.Command
import mx.fxmxgragfx.api.command.data.parameter.Param
import dev.cougar.core.util.string.CC
import org.bukkit.Bukkit
import org.bukkit.World
import org.bukkit.entity.Player

@Suppress("unused")
object TeleportWorldCommand {
    @JvmStatic
    @Command(names = ["tpworld"], permission = "core.tpworld")
    fun execute(player: Player, @Param(name = "world") worldName: String) {
        val world: World = Bukkit.getWorld(worldName)
        player.teleport(world.spawnLocation)
        player.sendMessage(CC.GOLD.toString() + "Teleported you to " + world.name)
    }
}