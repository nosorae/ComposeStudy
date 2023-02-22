package com.study.compose.tdd

import kotlin.reflect.full.functions

class WasRun(var wasRun: Boolean = false, val name: String) {
    fun testMethod() {
        wasRun = true
    }

    operator fun invoke() {
        this::class.java.methods.find { func ->
            println("func ${func.name}")
            func.name == name
        }?.invoke(Unit)
    }
}