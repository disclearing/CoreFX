package mx.fxmxgragfx.api.scoreboard

import org.bukkit.entity.Player
import java.util.*

interface ScoreGetter {

    fun getScores(scores: LinkedList<String>, player: Player)

}