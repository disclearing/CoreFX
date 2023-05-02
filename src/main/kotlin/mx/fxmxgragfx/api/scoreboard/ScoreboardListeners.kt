package mx.fxmxgragfx.api.scoreboard

import dev.cougar.core.Core
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent

class ScoreboardListeners : Listener {

    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        Core.getInstance().scoreboardEngine.create(event.player)
    }

    @EventHandler
    fun onPlayerQuit(event: PlayerQuitEvent) {
        Core.getInstance().scoreboardEngine.remove(event.player)
    }

}