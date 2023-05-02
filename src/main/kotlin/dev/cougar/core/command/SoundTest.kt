package dev.cougar.core.command

import mx.fxmxgragfx.api.command.Command
import mx.fxmxgragfx.api.command.data.parameter.Param
import org.bukkit.Sound
import org.bukkit.entity.Player

@Suppress("unused")
object SoundTest {
    @JvmStatic
    @Command(names = ["soundtest"], permission = "op")
    fun execute(
        player: Player,
        @Param(name = "sound") soundName: String?,
        @Param(name = "d1") d1: Double,
        @Param(name = "d2") d2: Double
    ) {
        val sounds = Sound.values()
        var soundToPlay: Sound? = null
        for (sound in sounds) {
            if (sound.name.equals(soundName, ignoreCase = true)) {
                soundToPlay = sound
                break
            }
        }
        player.playSound(player.location, soundToPlay, d1.toFloat(), d2.toFloat())
        player.sendMessage("Played $soundToPlay")
    }
}