package mx.fxmxgragfx.api.command.data.parameter

import mx.fxmxgragfx.api.command.data.Data

data class ParameterData(val name: String,
                         val defaultValue: String,
                         val type: Class<*>,
                         val wildcard: Boolean,
                         val methodIndex: Int,
                         val tabCompleteFlags: Set<String>,
                         val parameterType: Class<*>?) : Data