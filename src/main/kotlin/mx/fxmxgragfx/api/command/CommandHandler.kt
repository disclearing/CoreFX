package mx.fxmxgragfx.api.command

import mx.fxmxgragfx.api.classes.ClassUtils
import mx.fxmxgragfx.api.command.bukkit.ExtendedCommand
import mx.fxmxgragfx.api.command.bukkit.ExtendedCommandMap
import mx.fxmxgragfx.api.command.bukkit.ExtendedHelpTopic
import mx.fxmxgragfx.api.command.data.method.MethodProcessor
import mx.fxmxgragfx.api.command.data.parameter.ParameterType
import mx.fxmxgragfx.api.command.data.parameter.impl.*
import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.OfflinePlayer
import org.bukkit.World
import org.bukkit.command.Command
import org.bukkit.command.CommandMap
import org.bukkit.command.SimpleCommandMap
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.java.JavaPlugin
import java.lang.reflect.Field
import java.lang.reflect.Method
import java.util.*

@Suppress("UNCHECKED_CAST")
class CommandHandler {

    companion object {
        val rootNode: CommandNode = CommandNode()
    }

    val parameterTypeMap: MutableMap<Class<*>, ParameterType<*>> = HashMap()
    private val commandMap: CommandMap
    private val knownCommands: MutableMap<String, Command>

    init {
        val commandMapField = Bukkit.getServer().javaClass.getDeclaredField("commandMap")
        commandMapField.isAccessible = true

        commandMap = commandMapField.get(Bukkit.getServer()) as CommandMap

        val knownCommandsField = SimpleCommandMap::class.java.getDeclaredField("knownCommands")
        knownCommandsField.isAccessible = true

        knownCommands = knownCommandsField.get(commandMap) as MutableMap<String, Command>
    }

    fun load() {
        registerParameterType(Boolean::class.java, BooleanParameterType())
        registerParameterType(Integer::class.java, IntegerParameterType())
        registerParameterType(Int::class.java, IntegerParameterType())
        registerParameterType(Double::class.java, DoubleParameterType())
        registerParameterType(Float::class.java, FloatParameterType())
        registerParameterType(String::class.java, StringParameterType())
        registerParameterType(GameMode::class.java, GameModeParameterType())
        registerParameterType(Player::class.java, PlayerParameterType())
        registerParameterType(World::class.java, WorldParameterType())
        registerParameterType(OfflinePlayer::class.java, OfflinePlayerParameterType())

        swapCommandMap()
    }

    fun registerParameterType(clazz: Class<*>, parameterType: ParameterType<*>) {
        parameterTypeMap[clazz] = parameterType
    }

    fun getParameterType(clazz: Class<*>): ParameterType<*>? {
        return parameterTypeMap[clazz]
    }

    private fun swapCommandMap() {
        val commandMapField = Bukkit.getServer().javaClass.getDeclaredField("commandMap")
        commandMapField.isAccessible = true

        val oldCommandMap = commandMapField.get(Bukkit.getServer())
        val newCommandMap = ExtendedCommandMap(Bukkit.getServer())

        val knownCommandsField = SimpleCommandMap::class.java.getDeclaredField("knownCommands")
        knownCommandsField.isAccessible = true

        val modifiersField = Field::class.java.getDeclaredField("modifiers")
        modifiersField.isAccessible = true
        modifiersField.setInt(knownCommandsField, knownCommandsField.modifiers and -0x11)

        knownCommandsField.set(newCommandMap, knownCommandsField.get(oldCommandMap))
        commandMapField.set(Bukkit.getServer(), newCommandMap)
    }

    fun registerClass(clazz: Class<*>) {
        for (method in clazz.methods) {
            registerMethod(method)
        }
    }

    private fun registerMethod(method: Method) {
        method.isAccessible = true

        MethodProcessor().process(method)?.forEach { node ->
            val command = ExtendedCommand(node, JavaPlugin.getProvidingPlugin(method.declaringClass))

            register(command)

            node.children.values.forEach { child ->
                registerHelpTopic(child, node.aliases)
            }
        }
    }

    private fun register(command: ExtendedCommand) {
        val iterator = knownCommands.iterator()

        while (iterator.hasNext()) {
            val entry = iterator.next()
            if (entry.value.name.equals(command.name, true)) {
                entry.value.unregister(commandMap)
                iterator.remove()
            }
        }

        command.aliases.forEach { alias ->
            knownCommands[alias] = command
        }

        command.register(commandMap)
        knownCommands[command.name] = command
    }

    private fun registerHelpTopic(node: CommandNode, aliases: Set<String>?) {
        if (node.method != null) {
            Bukkit.getHelpMap().addTopic(ExtendedHelpTopic(node, aliases))
        }

        if (node.hasCommands()) {
            node.children.values.forEach { child ->
                registerHelpTopic(child, null)
            }
        }
    }

    private fun registerPackage(plugin: Plugin, packageName: String) {
        ClassUtils.getClassesInPackage(plugin, packageName).forEach(this::registerClass)
    }

    fun registerAll(plugin: Plugin) {
        registerPackage(plugin, plugin::class.java.`package`.name)
    }

}