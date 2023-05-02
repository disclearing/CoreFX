package dev.cougar.core.util.item

import com.google.common.collect.ImmutableMap
import org.bukkit.enchantments.Enchantment
import java.util.*

@Suppress("MemberVisibilityCanBePrivate")
object EnchantmentUtil {
    @JvmField
    var ENCHANTMENTS = ImmutableMap.builder<String, Enchantment>()
        .put("sharpness", Enchantment.DAMAGE_ALL)
        .put("sharp", Enchantment.DAMAGE_ALL)
        .put("damageall", Enchantment.DAMAGE_ALL)
        .put("baneofarthropods", Enchantment.DAMAGE_ARTHROPODS)
        .put("undeaddamage", Enchantment.DAMAGE_UNDEAD)
        .put("smite", Enchantment.DAMAGE_UNDEAD)
        .put("fireaspect", Enchantment.FIRE_ASPECT)
        .put("fire", Enchantment.FIRE_ASPECT)
        .put("knockback", Enchantment.KNOCKBACK)
        .put("looting", Enchantment.LOOT_BONUS_MOBS)
        .put("digspeed", Enchantment.DIG_SPEED)
        .put("efficiency", Enchantment.DIG_SPEED)
        .put("durability", Enchantment.DURABILITY)
        .put("unbreaking", Enchantment.DURABILITY)
        .put("fortune", Enchantment.LOOT_BONUS_BLOCKS)
        .put("silktouch", Enchantment.SILK_TOUCH)
        .put("protection", Enchantment.PROTECTION_ENVIRONMENTAL)
        .put("protect", Enchantment.PROTECTION_ENVIRONMENTAL)
        .put("envprotection", Enchantment.PROTECTION_ENVIRONMENTAL)
        .put("protectionenvironmental", Enchantment.PROTECTION_ENVIRONMENTAL)
        .put("protectionexplosions", Enchantment.PROTECTION_EXPLOSIONS)
        .put("protectionfall", Enchantment.PROTECTION_FALL)
        .put("expprot", Enchantment.PROTECTION_EXPLOSIONS)
        .put("blastprotect", Enchantment.PROTECTION_EXPLOSIONS)
        .put("featherfall", Enchantment.PROTECTION_FALL)
        .put("fallprot", Enchantment.PROTECTION_FALL)
        .put("fireprotect", Enchantment.PROTECTION_FIRE)
        .put("flameprotect", Enchantment.PROTECTION_FIRE)
        .put("fireprot", Enchantment.PROTECTION_FIRE)
        .put("projectileprotection", Enchantment.PROTECTION_PROJECTILE)
        .put("projprot", Enchantment.PROTECTION_PROJECTILE)
        .put("thorns", Enchantment.THORNS)
        .put("aquaaffinity", Enchantment.WATER_WORKER)
        .put("flame", Enchantment.ARROW_FIRE)
        .put("arrowdamage", Enchantment.ARROW_DAMAGE)
        .put("power", Enchantment.ARROW_DAMAGE)
        .put("arrowknockback", Enchantment.ARROW_KNOCKBACK)
        .put("arrowkb", Enchantment.ARROW_KNOCKBACK)
        .put("punch", Enchantment.ARROW_KNOCKBACK)
        .put("infarrows", Enchantment.ARROW_INFINITE)
        .put("infinity", Enchantment.ARROW_INFINITE)
        .put("infinite", Enchantment.ARROW_INFINITE)
        .put("luckofsea", Enchantment.LUCK)
        .put("lure", Enchantment.LURE)
        .build()!!

    @JvmStatic
    fun getByName(name: String): Enchantment? {
        return ENCHANTMENTS[name.lowercase(Locale.getDefault())]
    }
}