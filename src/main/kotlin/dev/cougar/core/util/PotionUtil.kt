package dev.cougar.core.util

import org.apache.commons.lang.StringUtils
import org.bukkit.potion.PotionEffectType
import java.util.*

object PotionUtil {
    @JvmStatic
    fun getName(potionEffectType: PotionEffectType): String {
        return if (potionEffectType.name.equals("fire_resistance", ignoreCase = true)) {
            "Fire Resistance"
        } else if (potionEffectType.name.equals("speed", ignoreCase = true)) {
            "Speed"
        } else if (potionEffectType.name.equals("weakness", ignoreCase = true)) {
            "Weakness"
        } else if (potionEffectType.name.equals("slowness", ignoreCase = true)) {
            "Slowness"
        } else {
            StringUtils.capitalize(
                potionEffectType.name.replace("_", " ").lowercase(Locale.getDefault())
            )
        }
    }
}