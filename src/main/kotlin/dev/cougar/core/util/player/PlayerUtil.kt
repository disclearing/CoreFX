package dev.cougar.core.util.player

import org.bukkit.entity.Player
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import org.bukkit.GameMode
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.net.URL
import java.util.*
import java.util.function.Consumer

@Suppress("unused")
object PlayerUtil {
    @JvmStatic
    fun denyMovement(player: Player) {
        player.walkSpeed = 0.0f
        player.flySpeed = 0.0f
        player.foodLevel = 0
        player.isSprinting = false
        player.addPotionEffect(PotionEffect(PotionEffectType.JUMP, Int.MAX_VALUE, 200))
    }

    @JvmStatic
    fun allowMovement(player: Player) {
        player.walkSpeed = 0.2f
        player.flySpeed = 0.1f
        player.foodLevel = 20
        player.isSprinting = true
        player.removePotionEffect(PotionEffectType.JUMP)
    }

    @JvmStatic
    fun reset(player: Player, resetHeldSlot: Boolean) {
        player.setHealth(20.0)
        player.saturation = 20.0f
        player.fallDistance = 0.0f
        player.foodLevel = 20
        player.fireTicks = 0
        player.maximumNoDamageTicks = 20
        player.exp = 0.0f
        player.level = 0
        player.allowFlight = false
        player.isFlying = false
        player.gameMode = GameMode.SURVIVAL
        player.inventory.armorContents = arrayOfNulls(4)
        player.inventory.contents = arrayOfNulls(36)
        player.activePotionEffects.forEach(Consumer { effect: PotionEffect -> player.removePotionEffect(effect.type) })
        if (resetHeldSlot) {
            player.inventory.heldItemSlot = 0
        }
        player.updateInventory()
    }

    @JvmStatic
    fun isLiked(uuid: UUID): Boolean {
        try {
            val url = URL("https://api.namemc.com/server/cougar.rip/likes?profile=$uuid")
            val connection = url.openConnection()
            connection.setRequestProperty(
                "User-Agent",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (HTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11"
            )
            val bufferedReader = BufferedReader(InputStreamReader(connection.getInputStream()))
            var line: String
            while (bufferedReader.readLine().also { line = it } != null) {
                line = line.lowercase(Locale.getDefault())
                if (line.contains("true")) {
                    return true
                }
            }
            bufferedReader.close()
        } catch (ignored: Exception) {
        }
        return false
    }
}