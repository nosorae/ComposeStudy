package com.study.compose.tdd

import kotlin.reflect.full.memberFunctions

class WasRun(
    override val name: String
) : TestCase(name) {
    var wasRun: Boolean = false
    var log: String = ""

    fun testMethod() {
        wasRun = true
    }

    override fun setUp() {
        wasRun = false
        log = "setUp "
    }
}

class TestCaseTest(override val name: String): TestCase(name) {
    lateinit var test: WasRun

    override fun setUp() {
        test = WasRun(name = "testMethod")
    }

    fun testRunning() {
        test()
        assert(test.wasRun)
    }

    fun testSetUp() {
        test()
        assert("setUp " == test.log)
    }
}

abstract class TestCase(open val name: String) {
    open fun setUp() {
        // do nothing
    }

    operator fun invoke() {
        setUp()
        val func = this::class.memberFunctions.find { func ->
            func.name == name
        } ?: throw RuntimeException("Can't find method named '$name'")
        func.call(this)
    }
}