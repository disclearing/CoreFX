package mx.fxmxgragfx.api.menu.button

import mx.fxmxgragfx.api.menu.Button
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.inventory.ItemStack

class DisplayButton(var itemStack: ItemStack, var isCancel: Boolean) : Button() {
    override fun getButtonItem(player: Player?): ItemStack {
        return if (itemStack == null) {
            ItemStack(Material.AIR)
        } else {
            itemStack
        }
    }

    override fun shouldCancel(player: Player?, clickType: ClickType?): Boolean {
        return isCancel
    }
}