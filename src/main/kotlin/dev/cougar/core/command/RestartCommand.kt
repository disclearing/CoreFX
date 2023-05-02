package dev.cougar.core.command

import mx.fxmxgragfx.api.command.Command
import mx.fxmxgragfx.api.command.data.parameter.Param
import dev.cougar.core.Core
import dev.cougar.core.task.RestartTask
import dev.cougar.core.util.duration.Duration
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitTask

@Suppress("unused")
object RestartCommand {
    @JvmStatic
    @Command(names = ["restart"], permission = "command.restart")
    fun restart(player: Player) {
        player.sendMessage(ChatColor.YELLOW.toString() + "/restart start <time>")
        player.sendMessage(ChatColor.YELLOW.toString() + "/restart cancel")
        player.sendMessage("")
        player.sendMessage(ChatColor.GRAY.toString() + "For example: /restart start 15m")
    }

    @JvmStatic
    @Command(names = ["restart start"], permission = "command.restart")
    fun restart(player: Player, @Param(name = "duration") duration: Duration?) {
        if (duration == null || duration.isPermanent || duration.value <= 0) {
            player.sendMessage(ChatColor.RED.toString() + "Please input a valid duration. For example 15m.")
            return
        }
        restart(duration)
    }

    @JvmStatic
    @Command(names = ["restart cancel"], permission = "command.restart")
    fun cancel(player: Player) {
        cancelRestart()
        player.sendMessage(ChatColor.YELLOW.toString() + "Restart cancelled.")
    }

    private var restarting = false
    private var restartingTask: BukkitTask? = null
    private fun restart(duration: Duration) {
        val seconds = duration.value.toInt() / 1000
        if (restartingTask != null) {
            restartingTask!!.cancel()
        }
        restartingTask = RestartTask(seconds, Bukkit.getServer().hasWhitelist())
            .runTaskTimer(Core.getInstance(), 0, 20)
        restarting = true
    }

    private fun cancelRestart() {
        if (restartingTask == null) return
        restartingTask!!.cancel()
        restarting = false
    }
}