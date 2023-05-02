package mx.fxmxgragfx.api.callback

import java.io.Serializable

interface TypeCallback<T> : Serializable {
    fun callback(data: T)
}