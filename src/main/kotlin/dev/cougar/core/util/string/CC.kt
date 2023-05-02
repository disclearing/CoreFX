package dev.cougar.core.util.string

import org.bukkit.ChatColor
import java.util.*
import kotlin.streams.toList

@Suppress("unused")
object CC {
    @JvmField
    val BLUE = ChatColor.BLUE
    @JvmField
    val AQUA = ChatColor.AQUA
    @JvmField
    val YELLOW = ChatColor.YELLOW
    @JvmField
    val RED = ChatColor.RED
    @JvmField
    val GRAY = ChatColor.GRAY
    @JvmField
    val GOLD = ChatColor.GOLD
    @JvmField
    val GREEN = ChatColor.GREEN
    @JvmField
    val WHITE = ChatColor.WHITE
    @JvmField
    val BLACK = ChatColor.BLACK
    @JvmField
    val BOLD = ChatColor.BOLD
    @JvmField
    val ITALIC = ChatColor.ITALIC
    @JvmField
    val UNDER_LINE = ChatColor.UNDERLINE
    @JvmField
    val STRIKE_THROUGH = ChatColor.STRIKETHROUGH
    @JvmField
    val RESET = ChatColor.RESET
    @JvmField
    val MAGIC = ChatColor.MAGIC
    @JvmField
    val DARK_BLUE = ChatColor.DARK_BLUE
    @JvmField
    val DARK_AQUA = ChatColor.DARK_AQUA
    @JvmField
    val DARK_GRAY = ChatColor.DARK_GRAY
    @JvmField
    val DARK_GREEN = ChatColor.DARK_GREEN
    @JvmField
    val DARK_PURPLE = ChatColor.DARK_PURPLE
    @JvmField
    val DARK_RED = ChatColor.DARK_RED
    @JvmField
    val PINK = ChatColor.LIGHT_PURPLE
    @JvmField
    val MENU_BAR = "${ChatColor.DARK_GRAY}${ChatColor.STRIKETHROUGH}------------------------"
    @JvmField
    val CHAT_BAR = "${ChatColor.DARK_GRAY}${ChatColor.STRIKETHROUGH }------------------------------------------------"
    @JvmField
    val SB_BAR = "${ChatColor.DARK_GRAY}${ChatColor.STRIKETHROUGH}----------------------"

    @JvmStatic
    fun translate(input: String): String {
        return ChatColor.translateAlternateColorCodes('&', input)
    }

    @JvmStatic
    fun translate(input: List<String>): List<String> {
        return input.stream().map(CC::translate).toList()
    }

    @JvmStatic
    fun translate(lines: Array<String>?): List<String> {
        return Arrays.stream(lines).map(CC::translate).toList()
    }
}