package mx.fxmxgragfx.api.scoreboard

import dev.cougar.core.Core

class ScoreboardThread : Thread("Cougar - Scoreboard Thread") {

    init {
        this.isDaemon = true
    }

    override fun run() {
        while (true) {
            for (online in Core.getOnlinePlayers()) {
                try {
                    Core.getInstance().scoreboardEngine.updateScoreboard(online)
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }

            try {
                sleep(Core.getInstance().scoreboardEngine.updateInterval * 50L)
            } catch (e2: InterruptedException) {
                e2.printStackTrace()
            }

        }
    }

}