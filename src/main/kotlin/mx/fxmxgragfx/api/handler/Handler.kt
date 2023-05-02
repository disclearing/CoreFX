package mx.fxmxgragfx.api.handler

import dev.cougar.core.Core
import org.bukkit.Bukkit
import org.bukkit.event.Listener


/**
 * Developed by FxMxGRAGFX
 * Project: Core
 **/

@Suppress("unused")
class Handler {

     constructor(inputClass : Class<out Listener>) {
         Bukkit.getServer().pluginManager.registerEvents(inputClass.newInstance(), Core.getInstance())
     }

    constructor(inputClass : Class<out Listener>, vararg args : Any) {
        Bukkit.getServer().pluginManager.registerEvents(inputClass.constructors.first().newInstance(*args) as Listener, Core.getInstance())
    }
}