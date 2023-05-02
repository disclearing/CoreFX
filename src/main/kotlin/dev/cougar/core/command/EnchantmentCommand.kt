package dev.cougar.core.command

import mx.fxmxgragfx.api.command.Command
import mx.fxmxgragfx.api.command.data.parameter.Param
import org.bukkit.entity.Player
import dev.cougar.core.util.string.CC
import dev.cougar.core.util.item.EnchantmentUtil
import dev.cougar.core.Core
import org.apache.commons.lang.StringUtils
import java.util.*

@Suppress("unused")
object EnchantmentCommand {
    @JvmStatic
    @Command(names = ["enchantment"], permission = "command.enchantment")
    fun execute(player: Player, @Param(name = "enchantment") enchantment: String, @Param(name = "level") level: String) {
        val hand = player.itemInHand
        if (hand == null) {
            player.sendMessage(CC.translate("&cYou don't have any item in your hand."))
            return
        }
        if (EnchantmentUtil.getByName(enchantment) == null) {
            player.sendMessage(CC.translate("&cEnchantment not found."))
            return
        }
        hand.addUnsafeEnchantment(EnchantmentUtil.getByName(enchantment), level.toInt())
        player.sendMessage(
            CC.translate(
                "&eApplied &c" + enchantment + " " + level + " &eto &c"
                        + StringUtils.capitalize(hand.type.name.lowercase(Locale.getDefault()).replace("_", " "))
            )
        )
        Core.getOnlinePlayers().forEach { staff: Player ->
            if (staff.hasPermission("core.enchantment.alert")) {
                staff.sendMessage(
                    CC.translate(
                        "&7(" + player.name + "&7) &eApplied &c" + enchantment + " " + level + " &eto &c"
                                + StringUtils.capitalize(
                            hand.type.name.lowercase(Locale.getDefault()).replace("_", " ")
                        )
                    )
                )
            }
        }
    }
}