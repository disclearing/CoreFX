package mx.fxmxgragfx.api.menu.pagination

import java.util.function.Predicate

class PageFilter<T>(val name: String, val predicate: Predicate<T>, var isEnabled: Boolean) {
    fun test(t: T): Boolean {
        return !isEnabled || predicate.test(t)
    }

    override fun equals(`object`: Any?): Boolean {
        return `object` is PageFilter<*> && `object`.name == name
    }
}