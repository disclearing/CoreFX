package mx.fxmxgragfx.api.async


/**
 * Developed by FxMxGRAGFX
 * Project: Core
 **/

class Async {

    constructor(unit: () -> Unit) {
        Thread{ unit.invoke() }.start()
    }

    constructor(runnable : Runnable) {
        Thread(runnable).start()
    }
}