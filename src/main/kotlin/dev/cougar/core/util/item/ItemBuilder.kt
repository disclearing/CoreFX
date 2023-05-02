package dev.cougar.core.util.item

import org.bukkit.ChatColor
import org.bukkit.DyeColor
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.enchantments.EnchantmentTarget
import org.bukkit.event.Listener
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.SkullMeta

@Suppress("unused", "DEPRECATION")
class ItemBuilder : Listener {
    private var `is`: ItemStack? = null

    constructor(mat: Material?) {
        `is` = ItemStack(mat)
    }

    constructor()

    constructor(`is`: ItemStack?) {
        this.`is` = `is`
    }

    fun amount(amount: Int): ItemBuilder {
        `is`!!.amount = amount
        return this
    }

    fun name(name: String?): ItemBuilder {
        val meta = `is`!!.itemMeta
        meta.displayName = ChatColor.translateAlternateColorCodes('&', name)
        `is`!!.itemMeta = meta
        return this
    }

    fun dyeColor(dyeColor: DyeColor): ItemBuilder {
        if (`is`!!.type == Material.WOOL) {
            `is`!!.durability = dyeColor.woolData.toShort()
        }
        return this
    }

    fun lore(name: String?): ItemBuilder {
        val meta = `is`!!.itemMeta
        var lore = meta.lore
        if (lore == null) {
            lore = ArrayList()
        }
        lore.add(ChatColor.translateAlternateColorCodes('&', name))
        meta.lore = lore
        `is`!!.itemMeta = meta
        return this
    }

    fun lore(vararg lore: String?): ItemBuilder {
        val toSet: MutableList<String> = ArrayList()
        val meta = `is`!!.itemMeta
        for (string in lore) {
            toSet.add(ChatColor.translateAlternateColorCodes('&', string))
        }
        meta.lore = toSet
        `is`!!.itemMeta = meta
        return this
    }

    fun lore(lore: List<String?>): ItemBuilder {
        val toSet: MutableList<String> = ArrayList()
        val meta = `is`!!.itemMeta
        for (string in lore) {
            toSet.add(ChatColor.translateAlternateColorCodes('&', string))
        }
        meta.lore = toSet
        `is`!!.itemMeta = meta
        return this
    }

    fun durability(durability: Int): ItemBuilder {
        `is`!!.durability = durability.toShort()
        return this
    }

    fun enchantment(enchantment: Enchantment?, level: Int): ItemBuilder {
        `is`!!.addUnsafeEnchantment(enchantment, level)
        return this
    }

    fun enchantment(enchantment: Enchantment?): ItemBuilder {
        `is`!!.addUnsafeEnchantment(enchantment, 1)
        return this
    }

    fun type(material: Material?): ItemBuilder {
        `is`!!.type = material
        return this
    }

    fun clearLore(): ItemBuilder {
        val meta = `is`!!.itemMeta
        meta.lore = ArrayList()
        `is`!!.itemMeta = meta
        return this
    }

    fun clearEnchantments(): ItemBuilder {
        for (e in `is`!!.enchantments.keys) {
            `is`!!.removeEnchantment(e)
        }
        return this
    }

    fun skullOwner(owner: String?): ItemBuilder {
        val meta = `is`!!.itemMeta as SkullMeta
        meta.owner = owner
        `is`!!.itemMeta = meta
        return this
    }

    fun glow(): ItemBuilder {
        val meta = `is`!!.itemMeta
        meta.addEnchant(Glow(), 1, true)
        `is`!!.itemMeta = meta
        return this
    }

    fun build(): ItemStack? {
        return `is`
    }

    private class Glow : Enchantment(25) {
        override fun canEnchantItem(arg0: ItemStack): Boolean {
            return false
        }

        override fun conflictsWith(arg0: Enchantment): Boolean {
            return false
        }

        override fun getItemTarget(): EnchantmentTarget? {
            return null
        }

        override fun getMaxLevel(): Int {
            return 2
        }

        override fun getName(): String? {
            return null
        }

        override fun getStartLevel(): Int {
            return 1
        }
    }

    companion object {
        @JvmStatic
        fun registerGlow() {
            try {
                val f = Enchantment::class.java.getDeclaredField("acceptingNew")
                f.isAccessible = true
                f[null] = true
            } catch (e: Exception) {
                e.printStackTrace()
            }
            try {
                val glow = Glow()
                Enchantment.registerEnchantment(glow)
            } catch (e: IllegalArgumentException) {
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}