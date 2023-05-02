package mx.fxmxgragfx.api.menu.pagination

import dev.cougar.core.util.item.ItemBuilder
import mx.fxmxgragfx.api.menu.Button
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.inventory.ItemStack
import java.util.*

class PageButton(private val mod: Int, private val menu: PaginatedMenu) : Button() {
    override fun getButtonItem(player: Player?): ItemStack {
        return if (mod > 0) {
            if (hasNext(player)) {
                ItemBuilder(Material.REDSTONE_TORCH_ON)
                    .name(ChatColor.GREEN.toString() + "Next Page")
                    .lore(
                        Arrays.asList(
                            ChatColor.YELLOW.toString() + "Click here to jump",
                            ChatColor.YELLOW.toString() + "to the next page."
                        )
                    )
                    .build()!!
            } else {
                ItemBuilder(Material.LEVER)
                    .name(ChatColor.GRAY.toString() + "Next Page")
                    .lore(
                        Arrays.asList(
                            ChatColor.YELLOW.toString() + "There is no available",
                            ChatColor.YELLOW.toString() + "next page."
                        )
                    )
                    .build()!!
            }
        } else {
            if (hasPrevious(player)) {
                ItemBuilder(Material.REDSTONE_TORCH_ON)
                    .name(ChatColor.GREEN.toString() + "Previous Page")
                    .lore(
                        Arrays.asList(
                            ChatColor.YELLOW.toString() + "Click here to jump",
                            ChatColor.YELLOW.toString() + "to the previous page."
                        )
                    )
                    .build()!!
            } else {
                ItemBuilder(Material.LEVER)
                    .name(ChatColor.GRAY.toString() + "Previous Page")
                    .lore(
                        Arrays.asList(
                            ChatColor.YELLOW.toString() + "There is no available",
                            ChatColor.YELLOW.toString() + "previous page."
                        )
                    )
                    .build()!!
            }
        }
    }

    override fun clicked(player: Player?, clickType: ClickType?) {
        if (mod > 0) {
            if (hasNext(player)) {
                menu.modPage(player, mod)
                playNeutral(player!!)
            } else {
                playFail(player!!)
            }
        } else {
            if (hasPrevious(player)) {
                menu.modPage(player, mod)
                playNeutral(player!!)
            } else {
                playFail(player!!)
            }
        }
    }

    private fun hasNext(player: Player?): Boolean {
        val pg = menu.page + mod
        return menu.getPages(player) >= pg
    }

    private fun hasPrevious(player: Player?): Boolean {
        val pg = menu.page + mod
        return pg > 0
    }
}