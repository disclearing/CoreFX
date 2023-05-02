package mx.fxmxgragfx.api.menu.pagination

import mx.fxmxgragfx.api.menu.Button
import mx.fxmxgragfx.api.menu.Menu
import org.bukkit.entity.Player
import kotlin.math.ceil

abstract class PaginatedMenu : Menu() {
    var page = 1
    override fun getTitle(player: Player?): String? {
        return getPrePaginatedTitle(player)
    }

    /**
     * Changes the page number
     *
     * @param player player viewing the inventory
     * @param mod    delta to modify the page number by
     */
    fun modPage(player: Player?, mod: Int) {
        page += mod
        getButtons().clear()
        openMenu(player!!)
    }

    /**
     * Gets the pages from a player
     *
     * @param player player viewing the inventory
     * @return The
     */
    fun getPages(player: Player?): Int {
        val buttonAmount = getAllPagesButtons(player).size
        return if (buttonAmount == 0) {
            1
        } else ceil(buttonAmount / getMaxItemsPerPage().toDouble()).toInt()
    }

    override fun getButtons(player: Player?): MutableMap<Int, Button?> {
        val minIndex = ((page - 1).toDouble() * getMaxItemsPerPage()).toInt()
        val maxIndex = (page.toDouble() * getMaxItemsPerPage()).toInt()
        var topIndex = 0
        val buttons = HashMap<Int, Button?>()
        for (entry in getAllPagesButtons(player).entries) {
            var ind = entry.key
            if (ind in minIndex until maxIndex) {
                ind -= (getMaxItemsPerPage().toDouble() * (page - 1)).toInt() - 9
                buttons[ind] = entry.value
                if (ind > topIndex) {
                    topIndex = ind
                }
            }
        }
        buttons[0] = PageButton(-1, this)
        buttons[8] = PageButton(1, this)
        for (i in 1..7) {
            buttons[i] = placeholderButton
        }
        val global = getGlobalButtons(player)
        if (global != null) {
            for ((key, value) in global) {
                buttons[key] = value
            }
        }
        return buttons
    }

    private fun getMaxItemsPerPage(): Int {
        return 18
    }

    /**
     * @param player player viewing the inventory
     * @return a Map of button that returns items which will be present on all pages
     */
    open fun getGlobalButtons(player: Player?): Map<Int, Button>? {
        return null
    }

    /**
     * @param player player viewing the inventory
     * @return title of the inventory before the page number is added
     */
    abstract fun getPrePaginatedTitle(player: Player?): String?

    /**
     * @param player player viewing the inventory
     * @return a map of button that will be paginated and spread across pages
     */
    abstract fun getAllPagesButtons(player: Player?): Map<Int, Button?>

    init {
        isUpdateAfterClick = false
    }
}