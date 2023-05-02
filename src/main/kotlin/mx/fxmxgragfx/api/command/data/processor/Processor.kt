package mx.fxmxgragfx.api.command.data.processor

interface Processor<T, R> {

    fun process(type: T): R

}