package mx.fxmxgragfx.api.scoreboard

import dev.cougar.core.Core
import org.bukkit.entity.Player

class ScoreboardEngine {
    private val boards: HashMap<String, Scoreboard> = HashMap()
    var configuration: ScoreboardConfiguration? = null
    var updateInterval: Int = 2

    fun load() {
        ScoreboardThread().start()
        Core.getInstance().server.pluginManager.registerEvents(ScoreboardListeners(), Core.getInstance())
    }

    internal fun create(player: Player) {
        if (configuration != null) {
            boards[player.name] = Scoreboard(player)
        }
    }

    internal fun updateScoreboard(player: Player) {
        boards[player.name]?.update()
    }

    internal fun remove(player: Player) {
        boards.remove(player.name)
    }
}