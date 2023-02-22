package com.study.compose.tdd

import kotlin.reflect.full.memberFunctions

class WasRun(var wasRun: Boolean = false, override val name: String) : TestCase(name) {
    fun testMethod() {
        wasRun = true
    }
}

abstract class TestCase(open val name: String) {
    operator fun invoke() {
        val func = this::class.memberFunctions.find { func ->
            func.name == name
        } ?: throw RuntimeException("Can't find method named '$name'")
        func.call(this)
    }
}