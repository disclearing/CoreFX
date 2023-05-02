package mx.fxmxgragfx.api.menu

import org.apache.commons.lang.StringUtils
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.inventory.ItemStack

abstract class Button {
    abstract fun getButtonItem(player: Player?): ItemStack
    open fun clicked(player: Player?, clickType: ClickType?) {}
    fun clicked(player: Player?, slot: Int, clickType: ClickType?, hotbarSlot: Int) {}
    open fun shouldCancel(player: Player?, clickType: ClickType?): Boolean {
        return true
    }

    open fun shouldUpdate(player: Player?, clickType: ClickType?): Boolean {
        return false
    }

    companion object {
        fun placeholder(material: Material?, data: Byte, vararg title: String?): Button {
            return object : Button() {
                override fun getButtonItem(player: Player?): ItemStack {
                    val it: ItemStack = ItemStack(material, 1,0, data)
                    val meta = it.itemMeta
                    meta.displayName = StringUtils.join(title)
                    it.itemMeta = meta
                    return it
                }
            }
        }

        @JvmStatic
        fun playFail(player: Player) {
            player.playSound(player.location, Sound.DIG_GRASS, 20f, 0.1f)
        }

        fun playSuccess(player: Player) {
            player.playSound(player.location, Sound.NOTE_PIANO, 20f, 15f)
        }

        @JvmStatic
        fun playNeutral(player: Player) {
            player.playSound(player.location, Sound.CLICK, 20f, 1f)
        }
    }
}