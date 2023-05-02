package dev.cougar.core.command

import mx.fxmxgragfx.api.command.Command
import org.bukkit.command.CommandSender
import dev.cougar.core.util.string.CC
import org.bukkit.Bukkit
import org.bukkit.World
import org.bukkit.entity.EntityType

@Suppress("unused")
object KillallCommand {
    @JvmStatic
    @Command(names = ["killall", "clearmobs"], permission = "command.killall", async = true)
    fun execute(sender: CommandSender) {
        sender.sendMessage(CC.CHAT_BAR)
        for (world in Bukkit.getWorlds()) {
            sender.sendMessage(CC.GREEN.toString() + "Cleared " + CC.WHITE.toString() + clearEntities(world) + CC.GREEN.toString() + " entities from " + CC.WHITE + world.name)
        }
        sender.sendMessage(CC.CHAT_BAR)
    }

    private fun clearEntities(world: World): Int {
        var removed = 0
        for (entity in world.entities) {
            if (entity.type == EntityType.PLAYER) {
                continue
            }
            removed++
            entity.remove()
        }
        return removed
    }
}