package mx.fxmxgragfx.api.module


/**
 * Developed by FxMxGRAGFX
 * Project: Core
 **/

@Suppress("unused")
object ModulesManager {

    @JvmStatic
    val modules = mutableSetOf<Module>()

    @JvmStatic
    fun registerModule(inputClass: Class<out Module>) {
        val module : Module = inputClass.newInstance()
        module.init()
        modules.add(module)
    }

    @JvmStatic
    fun registerModule(inputClass: Class<out Module>, vararg values : Any) {
        val module : Module = inputClass.constructors.first().newInstance(*values) as Module
        module.init()
        modules.add(module)
    }
}