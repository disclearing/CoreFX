package mx.fxmxgragfx.api.task

import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit


/**
 * Developed by FxMxGRAGFX
 * Project: Core
 **/

@Suppress("unused")
class AsyncTask {
    private val scheduler = Executors.newScheduledThreadPool(1)

    constructor(inputClass : Class<out Thread>, initTime : Long, delay : Long) {
        scheduler.scheduleAtFixedRate(inputClass.newInstance(), initTime, delay, TimeUnit.SECONDS)
    }

    constructor(inputClass: Class<out Thread>, initTime : Long, delay : Long, vararg args : Any) {
        scheduler.scheduleAtFixedRate(inputClass.constructors.first().newInstance(*args) as Thread, initTime, delay, TimeUnit.SECONDS)
    }
}