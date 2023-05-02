package mx.fxmxgragfx.api.menu

import dev.cougar.core.Core
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.inventory.ClickType
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent

class MenuListener : Listener {
    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOW)
    fun onButtonPress(event: InventoryClickEvent) {
        val player = event.whoClicked as Player
        val openMenu = Menu.currentlyOpenedMenus[player.name]
        if (openMenu != null) {
            if (event.slot != event.rawSlot) {
                if (event.click == ClickType.SHIFT_LEFT || event.click == ClickType.SHIFT_RIGHT) {
                    event.isCancelled = true
                }
                return
            }
            if (openMenu.buttons.containsKey(event.slot)) {
                val button = openMenu.buttons[event.slot]
                val cancel = button!!.shouldCancel(player, event.click)
                if (!cancel && (event.click == ClickType.SHIFT_LEFT || event.click == ClickType.SHIFT_RIGHT)) {
                    event.isCancelled = true
                    if (event.currentItem != null) {
                        player.inventory.addItem(event.currentItem)
                    }
                } else {
                    event.isCancelled = cancel
                }
                button.clicked(player, event.click)
                button.clicked(player, event.slot, event.click, event.hotbarButton)
                if (Menu.currentlyOpenedMenus.containsKey(player.name)) {
                    val newMenu = Menu.currentlyOpenedMenus[player.name]
                    if (newMenu === openMenu) {
                        val buttonUpdate = button.shouldUpdate(player, event.click)
                        if (buttonUpdate) {
                            openMenu.isClosedByMenu = true
                            newMenu.openMenu(player)
                        }
                    }
                } else if (button.shouldUpdate(player, event.click)) {
                    openMenu.isClosedByMenu = true
                    openMenu.openMenu(player)
                }
                if (event.isCancelled) {
                    Bukkit.getScheduler().runTaskLater(Core.getInstance(), { player.updateInventory() }, 1L)
                }
            } else {
                if (event.currentItem != null) {
                    event.isCancelled = true
                }
                if (event.click == ClickType.SHIFT_LEFT || event.click == ClickType.SHIFT_RIGHT) {
                    event.isCancelled = true
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    fun onInventoryClose(event: InventoryCloseEvent) {
        val player = event.player as Player
        val openMenu = Menu.currentlyOpenedMenus[player.name]
        if (openMenu != null) {
            openMenu.onClose(player)
            Menu.currentlyOpenedMenus.remove(player.name)
        }
    }
}