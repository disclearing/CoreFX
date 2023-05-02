package dev.cougar.core.util.item

import org.bukkit.Color
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.inventory.meta.LeatherArmorMeta
import java.util.*

@Suppress("MemberVisibilityCanBePrivate", "unused", "NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS", "DEPRECATION")
object InventoryUtil {
    @JvmStatic
    fun fixInventoryOrder(source: Array<ItemStack?>?): Array<ItemStack?> {
        val fixed = arrayOfNulls<ItemStack>(36)
        System.arraycopy(source, 0, fixed, 27, 9)
        System.arraycopy(source, 9, fixed, 0, 27)
        return fixed
    }

    @JvmStatic
    fun serializeInventory(source: Array<ItemStack?>): String {
        val builder = StringBuilder()
        for (itemStack in source) {
            builder.append(serializeItemStack(itemStack))
            builder.append(";")
        }
        return builder.toString()
    }

    @JvmStatic
    fun deserializeInventory(source: String): Array<ItemStack?> {
        val items: MutableList<ItemStack?> = ArrayList()
        val split = source.split(";".toRegex()).toTypedArray()
        for (piece in split) {
            items.add(deserializeItemStack(piece))
        }
        return items.toTypedArray()
    }

    @JvmStatic
    fun serializeItemStack(item: ItemStack?): String {
        val builder = StringBuilder()
        if (item == null) {
            return "null"
        }
        val isType = item.type.id.toString()
        builder.append("t@").append(isType)
        if (item.hasItemMeta()) {
            val imeta = item.itemMeta
            if (item.type == Material.LEATHER_BOOTS
                || item.type == Material.LEATHER_CHESTPLATE
                || item.type == Material.LEATHER_HELMET
                || item.type == Material.LEATHER_LEGGINGS
            ) {
                val armorMeta = imeta as LeatherArmorMeta
                builder.append(":c@").append(armorMeta.color.asRGB())
            }
            if (imeta.hasDisplayName()) {
                builder.append(":dn@").append(imeta.displayName)
            }
            if (imeta.hasLore()) {
                builder.append(":l@").append(imeta.lore)
            }
        }
        if (item.durability.toInt() != 0) {
            val isDurability = item.durability.toString()
            builder.append(":d@").append(isDurability)
        }
        if (item.amount != 1) {
            val isAmount = item.amount.toString()
            builder.append(":a@").append(isAmount)
        }
        val isEnch = item.enchantments
        if (isEnch.isNotEmpty()) {
            for ((key, value) in isEnch) {
                builder.append(":e@").append(key.id).append("@").append(value)
            }
        }
        return builder.toString()
    }

    @JvmStatic
    fun deserializeItemStack(`in`: String): ItemStack? {
        var item: ItemStack? = null
        var meta: ItemMeta? = null
        if (`in` == "null") {
            return ItemStack(Material.AIR)
        }
        val split = `in`.split(":".toRegex()).toTypedArray()
        for (itemInfo in split) {
            val itemAttribute = itemInfo.split("@".toRegex()).toTypedArray()
            when (itemAttribute[0]) {
                "t" -> {
                    item = ItemStack(
                        Material.getMaterial(
                            Integer.valueOf(
                                itemAttribute[1]
                            )
                        )
                    )
                    meta = item.itemMeta
                }
                "c" -> {
                    if (item != null) {
                        val armorMeta = meta as LeatherArmorMeta?
                        armorMeta!!.color = Color.fromBGR(itemAttribute[1].toInt())
                        item.itemMeta = armorMeta
                        meta = item.itemMeta
                        break
                    }
                }
                "d" -> {
                    if (item != null) {
                        item.durability = itemAttribute[1].toShort()
                        break
                    }
                }
                "a" -> {
                    if (item != null) {
                        item.amount = Integer.valueOf(itemAttribute[1])
                        break
                    }
                }
                "e" -> {
                    if (item != null) {
                        item.addEnchantment(
                            Enchantment.getById(Integer.valueOf(itemAttribute[1])),
                            Integer.valueOf(itemAttribute[2])
                        )
                        break
                    }
                }
                "dn" -> {
                    if (meta != null) {
                        meta.displayName = itemAttribute[1]
                        break
                    }
                }
                "l" -> {
                    itemAttribute[1] = itemAttribute[1].replace("[", "")
                    itemAttribute[1] = itemAttribute[1].replace("]", "")
                    val lore = mutableListOf(*itemAttribute[1].split(",".toRegex()).toTypedArray())
                    var x = 0
                    while (x < lore.size) {
                        var s = lore[x]
                        if (s.toCharArray().isNotEmpty()) {
                            if (s[0] == ' ') {
                                s = s.replaceFirst(" ".toRegex(), "")
                            }
                            lore[x] = s
                        }
                        ++x
                    }
                    if (meta != null) {
                        meta.lore = lore
                        break
                    }
                }
            }
        }
        if (meta != null && (meta.hasDisplayName() || meta.hasLore())) {
            item!!.itemMeta = meta
        }
        return item
    }
}