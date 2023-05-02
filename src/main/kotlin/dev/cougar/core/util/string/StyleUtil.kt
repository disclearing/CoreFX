package dev.cougar.core.util.string

import org.apache.commons.lang.StringEscapeUtils

@Suppress("MemberVisibilityCanBePrivate", "unused")
object StyleUtil {
    @JvmStatic
    fun colorPing(ping: Int): String {
        return if (ping <= 50) {
            CC.GREEN.toString() + ping
        } else if (ping <= 80) {
            CC.YELLOW.toString() + ping
        } else if (ping <= 110) {
            CC.GOLD.toString() + ping
        } else {
            CC.RED.toString() + ping
        }
    }

    @JvmStatic
    fun colorHealth(health: Double): String {
        return if (health > 15) {
            CC.GREEN.toString() + convertHealth(health)
        } else if (health > 10) {
            CC.GOLD.toString() + convertHealth(health)
        } else if (health > 5) {
            CC.YELLOW.toString() + convertHealth(health)
        } else {
            CC.RED.toString() + convertHealth(health)
        }
    }

    @JvmStatic
    fun convertHealth(health: Double): Double {
        val dividedHealth = health / 2
        if (dividedHealth % 1 == 0.0) {
            return dividedHealth
        }
        if (dividedHealth % .5 == 0.0) {
            return dividedHealth
        }
        return if (dividedHealth - dividedHealth.toInt() > .5) {
            (dividedHealth.toInt() + 1).toDouble()
        } else if (dividedHealth - dividedHealth.toInt() > .25) {
            dividedHealth.toInt() + .5
        } else {
            dividedHealth.toInt().toDouble()
        }
    }

    val heartIcon: String
        get() = StringEscapeUtils.unescapeJava("\u2764")
}