package mx.fxmxgragfx.api.menu.pagination

import dev.cougar.core.util.item.ItemBuilder
import mx.fxmxgragfx.api.menu.Button
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.inventory.ItemStack

class PageInfoButton(private val menu: PaginatedMenu) : Button() {
    override fun getButtonItem(player: Player?): ItemStack {
        val pages = menu.getPages(player)
        return ItemBuilder(Material.PAPER)
            .name(ChatColor.GOLD.toString() + "Page Info")
            .lore(
                ChatColor.YELLOW.toString() + "You are viewing page #" + menu.page + ".",
                ChatColor.YELLOW.toString() + if (pages == 1) "There is 1 page." else "There are $pages pages.",
                "",
                ChatColor.YELLOW.toString() + "Middle click here to",
                ChatColor.YELLOW.toString() + "view all pages."
            )
            .build()!!
    }

    override fun clicked(player: Player?, clickType: ClickType?) {
        if (clickType == ClickType.RIGHT) {
            ViewAllPagesMenu(menu).openMenu(player!!)
            playNeutral(player)
        }
    }
}