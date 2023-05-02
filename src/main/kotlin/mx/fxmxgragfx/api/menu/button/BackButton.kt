package mx.fxmxgragfx.api.menu.button

import dev.cougar.core.util.item.ItemBuilder
import mx.fxmxgragfx.api.menu.Button
import mx.fxmxgragfx.api.menu.Menu
import dev.cougar.core.util.string.CC
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.inventory.ItemStack
import java.util.*

class BackButton(private val back: Menu) : Button() {
    override fun getButtonItem(player: Player?): ItemStack {
        return ItemBuilder(Material.REDSTONE)
            .name(CC.RED.toString() + CC.BOLD + "Back")
            .lore(
                Arrays.asList<String?>(
                    CC.RED.toString() + "Click here to return to",
                    CC.RED.toString() + "the previous menu."
                )
            )
            .build()!!
    }

    override fun clicked(player: Player?, clickType: ClickType?) {
        playNeutral(player!!)
        back.openMenu(player)
    }
}