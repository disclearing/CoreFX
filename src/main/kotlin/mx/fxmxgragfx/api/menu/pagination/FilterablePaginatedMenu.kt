package mx.fxmxgragfx.api.menu.pagination

import mx.fxmxgragfx.api.menu.Button
import org.bukkit.entity.Player
import java.util.HashMap
import java.util.ArrayList

abstract class FilterablePaginatedMenu<T> : PaginatedMenu() {
    var filters: List<PageFilter<T>>? = null
    var scrollIndex = 0
    override fun getGlobalButtons(player: Player?): Map<Int, Button>? {
        val buttons: MutableMap<Int, Button> = HashMap()
        buttons[7] = PageFilterButton(this)
        return buttons
    }

    override fun getAllPagesButtons(player: Player?): Map<Int, Button?> {
        return getFilteredButtons(player)
    }

    abstract fun getFilteredButtons(player: Player?): Map<Int, Button>
    fun generateFilters(): List<PageFilter<T>> {
        return ArrayList()
    }

    init {
        filters = generateFilters()
    }
}