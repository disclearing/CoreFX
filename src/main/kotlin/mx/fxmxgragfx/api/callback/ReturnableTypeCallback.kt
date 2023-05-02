package mx.fxmxgragfx.api.callback

interface ReturnableTypeCallback<T> {
    fun call(): T
}