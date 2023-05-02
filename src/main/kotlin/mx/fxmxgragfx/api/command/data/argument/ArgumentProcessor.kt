package mx.fxmxgragfx.api.command.data.argument

import mx.fxmxgragfx.api.command.data.flag.Flag
import mx.fxmxgragfx.api.command.data.processor.Processor
import java.util.*

class ArgumentProcessor : Processor<Array<String>, Arguments> {

    override fun process(type: Array<String>): Arguments {
        val flags = ArrayList<String>()
        val arguments = ArrayList<String>()

        for (s in type) {
            if (s.isNotEmpty()) {
                if (s[0] == '-' && s != "-" && matches(s)) {
                    val flag = getFlagName(s)
                    if (flag != null) {
                        flags.add(flag)
                    }
                } else {
                    arguments.add(s)
                }
            }
        }

        return Arguments(arguments, flags)
    }

    private fun getFlagName(flag: String): String? {
        val matcher = Flag.FLAG_PATTERN.matcher(flag)

        if (matcher.matches()) {
            val name = matcher.replaceAll("$2$3")
            return if (name.length == 1) name else name.lowercase(Locale.getDefault())
        }

        return null
    }

    private fun matches(flag: String): Boolean {
        return Flag.FLAG_PATTERN.matcher(flag).matches()
    }

}