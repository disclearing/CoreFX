package dev.cougar.core.command

import mx.fxmxgragfx.api.command.Command
import org.bukkit.command.CommandSender
import dev.cougar.core.util.string.CC
import dev.cougar.core.Core
import org.bukkit.Bukkit
import dev.cougar.core.util.DateUtil
import net.minecraft.server.v1_7_R4.MinecraftServer
import java.lang.management.ManagementFactory

@Suppress("unused")
object TPSCommand {
    @JvmStatic
    @Command(names = ["tps", "lag"], async = true, permission = "command.tps", hidden = true)
    fun execute(sender: CommandSender) {
        sender.sendMessage(CC.CHAT_BAR)
        sender.sendMessage(CC.translate("&6&lServer Info:"))
        sender.sendMessage(CC.translate("&7» &6Online&7: &f" + Core.getOnlinePlayers().size + "/" + Bukkit.getMaxPlayers()))
        sender.sendMessage(CC.translate("&7» &6Uptime&7: &f" + DateUtil.formatDateDiff(ManagementFactory.getRuntimeMXBean().startTime)))
        sender.sendMessage(CC.translate("&7» &6Last Tick Time: &f" + (System.currentTimeMillis() - MinecraftServer.currentTick) + "ms"))
        sender.sendMessage(CC.translate("&7» &6Max RAM&7: &f" + Runtime.getRuntime().maxMemory() / 1024 / 1024 + "MB"))
        sender.sendMessage(
            CC.translate(
                "&7» &6Alloc RAM&7: &f" + Runtime.getRuntime().totalMemory() / 1024 / 1024 + "MB"
            )
        )
        sender.sendMessage(
            CC.translate(
                "&7» &6Free RAM&7: &f" + Runtime.getRuntime().freeMemory() / 1024 / 1024
            ) + "MB"
        )
        sender.sendMessage(CC.CHAT_BAR)
        for (world in Bukkit.getWorlds()) sender.sendMessage(CC.translate("&7» &6" + world.name + "&7: &6Loaded Chunks&7: &6" + world.loadedChunks.size + ", &6Entities: &7" + world.entities.size))
        sender.sendMessage(CC.CHAT_BAR)
    }
}