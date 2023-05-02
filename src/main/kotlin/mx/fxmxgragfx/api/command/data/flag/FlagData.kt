package mx.fxmxgragfx.api.command.data.flag

import mx.fxmxgragfx.api.command.data.Data

data class FlagData(val names: List<String>,
                    val description: String,
                    val defaultValue: Boolean,
                    val methodIndex: Int) : Data