package mx.fxmxgragfx.api.menu.button

import mx.fxmxgragfx.api.menu.Button
import mx.fxmxgragfx.api.menu.Menu
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.inventory.ItemStack

class JumpToMenuButton(private val menu: Menu, private val itemStack: ItemStack) : Button() {
    override fun getButtonItem(player: Player?): ItemStack {
        return itemStack
    }

    override fun clicked(player: Player?, clickType: ClickType?) {
        menu.openMenu(player!!)
    }
}