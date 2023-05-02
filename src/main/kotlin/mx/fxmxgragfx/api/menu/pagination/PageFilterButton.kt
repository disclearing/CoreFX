package mx.fxmxgragfx.api.menu.pagination

import dev.cougar.core.util.item.ItemBuilder
import mx.fxmxgragfx.api.menu.Button
import dev.cougar.core.util.string.CC
import org.apache.commons.lang.StringEscapeUtils
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.inventory.ItemStack

class PageFilterButton<T>(private val menu: FilterablePaginatedMenu<T>) : Button() {
    override fun getButtonItem(player: Player?): ItemStack {
        if (menu.filters == null || menu.filters!!.isEmpty()) {
            return ItemStack(Material.AIR)
        }
        val lore: MutableList<String?> = ArrayList()
        lore.add(CC.MENU_BAR)
        for (filter in menu.filters!!) {
            var color: String
            var decoration = ""
            var icon: String
            if (filter.isEnabled) {
                color = ChatColor.GREEN.toString()
                icon = StringEscapeUtils.unescapeJava("\u2713")
            } else {
                color = ChatColor.RED.toString()
                icon = StringEscapeUtils.unescapeJava("\u2717")
            }
            if (menu.filters!![menu.scrollIndex].equals(filter)) {
                decoration = ChatColor.YELLOW.toString() + StringEscapeUtils.unescapeJava("» ") + " "
            }
            lore.add(decoration + color + icon + " " + filter.name)
        }
        lore.add(CC.MENU_BAR)
        lore.add("&eLeft click to scroll.")
        lore.add("&eRight click to toggle a filter.")
        lore.add(CC.MENU_BAR)
        return ItemBuilder(Material.HOPPER)
            .name("&7Filters")
            .lore(lore)
            .build()!!
    }

    override fun clicked(player: Player?, clickType: ClickType?) {
        if (menu.filters == null || menu.filters!!.isEmpty()) {
            player!!.sendMessage(ChatColor.RED.toString() + "There are no filters.")
        } else {
            if (clickType == ClickType.LEFT) {
                if (menu.scrollIndex == menu.filters!!.size - 1) {
                    menu.scrollIndex = 0
                } else {
                    menu.scrollIndex = menu.scrollIndex + 1
                }
            } else if (clickType == ClickType.RIGHT) {
                val filter = menu.filters!![menu.scrollIndex]
                filter.isEnabled = !filter.isEnabled
            }
        }
    }

    override fun shouldUpdate(player: Player?, clickType: ClickType?): Boolean {
        return true
    }
}