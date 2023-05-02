package mx.fxmxgragfx.api.menu

import dev.cougar.core.Core
import dev.cougar.core.util.string.CC.translate
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import org.bukkit.scheduler.BukkitTask

abstract class Menu {
    var kore = Core.getInstance()
    var buttons: MutableMap<Int, Button?> = HashMap()
    open var isAutoUpdate = false
    @JvmName("getButtons1")
    fun getButtons(): MutableMap<Int, Button?> {
        return buttons
    }

    @JvmName("setButtons1")
    fun setButtons(buttons: MutableMap<Int, Button?>) {
        this.buttons = buttons
    }

    var isUpdateAfterClick = true
    var isClosedByMenu = false
    var isPlaceholder = false
    var placeholderButton = Button.placeholder(Material.STAINED_GLASS_PANE, 0.toByte(), " ")
    var task: BukkitTask? = null
    private fun createItemStack(player: Player, button: Button?): ItemStack {
        val item = button!!.getButtonItem(player)
        if (item.type != Material.SKULL_ITEM) {
            val meta = item.itemMeta
            if (meta != null && meta.hasDisplayName()) {
                meta.displayName = meta.displayName + "§b§c§d§e"
            }
            item.itemMeta = meta
        }
        return item
    }

    fun openMenu(player: Player) {
        buttons = this.getButtons(player)
        val previousMenu = currentlyOpenedMenus[player.name]
        var inventory: Inventory? = null
        val size = if (size == -1) size(buttons) else size
        var update = false
        var title = translate(getTitle(player)!!)
        if (title.length > 32) {
            title = title.substring(0, 32)
        }
        if (player.openInventory != null) {
            if (previousMenu == null) {
                player.closeInventory()
            } else {
                val previousSize = player.openInventory.topInventory.size
                if (previousSize == size && player.openInventory.topInventory.title == title) {
                    inventory = player.openInventory.topInventory
                    update = true
                } else {
                    previousMenu.isClosedByMenu = true
                    player.closeInventory()
                }
            }
        }
        if (inventory == null) {
            inventory = Bukkit.createInventory(player, size, title)
        }
        inventory!!.contents = arrayOfNulls(inventory.size)
        currentlyOpenedMenus[player.name] = this
        for ((key, value) in buttons) {
            inventory.setItem(key, createItemStack(player, value))
        }
        if (isPlaceholder) {
            for (index in 0 until size) {
                if (buttons[index] == null) {
                    buttons[index] = placeholderButton
                    inventory.setItem(index, placeholderButton.getButtonItem(player))
                }
            }
        }
        if (update) {
            player.updateInventory()
        } else {
            player.openInventory(inventory)
        }
        onOpen(player)
        isClosedByMenu = false
        if (isAutoUpdate && task == null) {
            task = Core.getInstance().server.scheduler.runTaskTimer(Core.getInstance(), { openMenu(player) }, 20L, 20L)
        }
    }

    fun size(buttons: Map<Int, Button?>): Int {
        var highest = 0
        for (buttonValue in buttons.keys) {
            if (buttonValue > highest) {
                highest = buttonValue
            }
        }
        return (Math.ceil((highest + 1) / 9.0) * 9.0).toInt()
    }

    val size: Int
        get() = -1

    fun getSlot(x: Int, y: Int): Int {
        return 9 * y + x
    }

    abstract fun getTitle(player: Player?): String?
    abstract fun getButtons(player: Player?): MutableMap<Int, Button?>
    fun onOpen(player: Player?) {}
    fun onClose(player: Player?) {
        if (task != null) {
            task!!.cancel()
        }
    }

    companion object {
        @JvmField
        var currentlyOpenedMenus: MutableMap<String, Menu> = HashMap()
        fun getCurrentlyOpenedMenus(): Map<String, Menu> {
            return currentlyOpenedMenus
        }

        fun setCurrentlyOpenedMenus(currentlyOpenedMenus: MutableMap<String, Menu>) {
            Companion.currentlyOpenedMenus = currentlyOpenedMenus
        }
    }
}