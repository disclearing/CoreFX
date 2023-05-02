package mx.fxmxgragfx.api.command.data.method

import mx.fxmxgragfx.api.command.Command
import mx.fxmxgragfx.api.command.CommandHandler
import mx.fxmxgragfx.api.command.CommandNode
import mx.fxmxgragfx.api.command.data.Data
import mx.fxmxgragfx.api.command.data.flag.Flag
import mx.fxmxgragfx.api.command.data.flag.FlagData
import mx.fxmxgragfx.api.command.data.parameter.Param
import mx.fxmxgragfx.api.command.data.parameter.ParameterData
import mx.fxmxgragfx.api.command.data.processor.Processor
import dev.cougar.core.Core
import org.bukkit.command.CommandSender
import java.lang.reflect.Method
import java.util.*
import kotlin.collections.HashSet

class MethodProcessor : Processor<Method, Set<CommandNode>?> {

    override fun process(type: Method): Set<CommandNode>? {
        if (type.isAnnotationPresent(Command::class.java)) {
            if (type.parameterCount >= 1 && CommandSender::class.java.isAssignableFrom(type.parameterTypes[0])) {
                val command = type.getAnnotation(Command::class.java)
                val owningClass = type.declaringClass
                val flagNames = mutableListOf<String>()
                val allParams = mutableListOf<Data>()

                if (type.parameterCount > 1) {
                    for (i in 1 until type.parameterCount) {
                        val parameter = type.parameters[i]

                        if (parameter.isAnnotationPresent(Param::class.java)) {
                            val param: Param = parameter.getAnnotation(Param::class.java)
                            val hash = setOf(*param.tabCompleteFlags)
                            val data = ParameterData(param.name, param.defaultValue, parameter.type, param.wildcard, i, hash, Core.getInstance().commandHandler.parameterTypeMap[parameter.type]?.javaClass)
                            allParams.add(data)
                        } else {
                            if (!parameter.isAnnotationPresent(Flag::class.java)) {
                                throw IllegalArgumentException("Every data, other than the sender, must be annotated with Param")
                            }

                            val flag: Flag = parameter.getAnnotation(Flag::class.java)
                            val flagData = FlagData(listOf(*flag.value), flag.description, flag.defaultValue, i)

                            allParams.add(flagData)
                            flagNames.addAll(listOf(*flag.value))
                        }
                    }
                }

                val registered = HashSet<CommandNode>()

                for (name in command.names) {
                    val qualifiedName = name.lowercase(Locale.getDefault()).trim()
                    var hadChild = false
                    var cmdNames: Array<String> = arrayOf(qualifiedName)

                    if (qualifiedName.contains(" ")) {
                        cmdNames = qualifiedName.split(" ").toTypedArray()
                    }

                    val primaryName = cmdNames[0]
                    var workingNode = CommandNode(owningClass)

                    if (CommandHandler.rootNode.hasCommand(primaryName)) {
                        workingNode = CommandHandler.rootNode.getCommand(primaryName)!!
                        workingNode.aliases.add(primaryName)
                    } else {
                        workingNode.name = primaryName
                    }

                    var parentNode = CommandNode(owningClass)

                    if (workingNode.hasCommand(primaryName)) {
                        parentNode = workingNode.getCommand(primaryName)!!
                    } else {
                        parentNode.name = primaryName
                        parentNode.permission = ""
                    }

                    if (cmdNames.size > 1) {
                        hadChild = true

                        workingNode.registerCommand(parentNode)

                        var childNode = CommandNode(owningClass)

                        for (i in 1 until cmdNames.size) {
                            val subName = cmdNames[i]

                            childNode.name = subName

                            if (parentNode.hasCommand(subName)) {
                                childNode = parentNode.getCommand(subName)!!
                            }

                            parentNode.registerCommand(childNode)

                            if (i == cmdNames.size - 1) {
                                childNode.method = type
                                childNode.async = command.async
                                childNode.hidden = command.hidden
                                childNode.vipFeature = command.vipFeature
                                childNode.permission = command.permission
                                childNode.description = command.description
                                childNode.validFlags = flagNames
                                childNode.parameters = allParams
                                childNode.logToConsole = command.logToConsole
                            } else {
                                parentNode = childNode
                                childNode = CommandNode(owningClass)
                            }
                        }
                    }

                    if (!hadChild) {
                        parentNode.method = type
                        parentNode.async = command.async
                        parentNode.hidden = command.hidden
                        parentNode.vipFeature = command.vipFeature
                        parentNode.permission = command.permission
                        parentNode.description = command.description
                        parentNode.validFlags = flagNames
                        parentNode.parameters = allParams
                        parentNode.logToConsole = command.logToConsole

                        workingNode.registerCommand(parentNode)
                    }

                    CommandHandler.rootNode.registerCommand(workingNode)
                    registered.add(workingNode)
                }

                return registered
            }
        }

        return null
    }

}