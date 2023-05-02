package mx.fxmxgragfx.api.menu.button

import mx.fxmxgragfx.api.callback.TypeCallback
import mx.fxmxgragfx.api.menu.Button
import mx.fxmxgragfx.api.menu.Menu
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.inventory.ItemStack

class ConfirmationButton(
    private val confirm: Boolean,
    private val callback: TypeCallback<Boolean>,
    private val closeAfterResponse: Boolean
) : Button() {
    override fun getButtonItem(player: Player?): ItemStack {
        val itemStack: ItemStack = ItemStack(Material.WOOL, 1,0, if (confirm) 5.toByte() else 14.toByte())
        val itemMeta = itemStack.itemMeta
        itemMeta.displayName =
            if (confirm) ChatColor.GREEN.toString() + "Confirm" else ChatColor.RED.toString() + "Cancel"
        itemStack.itemMeta = itemMeta
        return itemStack
    }

    override fun clicked(player: Player?, clickType: ClickType?) {
        if (confirm) {
            player!!.playSound(player.location, Sound.NOTE_PIANO, 20f, 0.1f)
        } else {
            player!!.playSound(player.location, Sound.DIG_GRAVEL, 20f, 0.1f)
        }
        if (closeAfterResponse) {
            val menu = Menu.currentlyOpenedMenus[player.name]
            if (menu != null) {
                menu.isClosedByMenu = true
            }
            player.closeInventory()
        }
        callback.callback(confirm)
    }
}