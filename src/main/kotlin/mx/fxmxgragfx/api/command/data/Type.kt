package mx.fxmxgragfx.api.command.data

import kotlin.reflect.KClass

@Suppress("unused")
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.VALUE_PARAMETER)
annotation class Type(val value: KClass<*>)