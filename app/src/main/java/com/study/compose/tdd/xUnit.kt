package com.study.compose.tdd

import kotlin.reflect.full.memberFunctions
import kotlin.test.assertEquals

class WasRun(
    override val name: String
) : TestCase(name) {
    var wasRun: Boolean = false
    var log: String = ""

    fun testMethod() {
        wasRun = true
        log += "testMethod "
    }

    override fun setUp() {
        wasRun = false
        log += "setUp "
    }

    override fun tearDown() {
        log += "tearDown "
    }
}

class TestCaseTest(override val name: String): TestCase(name) {
    fun testTemplatedMethod() {
        val test = WasRun(name = "testMethod")
        test()
        assertEquals("setUp testMethod tearDown ", test.log)
    }

    fun testResult() {
        val test = WasRun(name = "testMethod")
        val result = test()
        assertEquals("1 run, 0 failed", result.summary())
    }
}

abstract class TestCase(open val name: String) {
    open fun setUp() {}
    open fun tearDown() {}

    operator fun invoke(): TestResult {
        val result = TestResult()
        result.testStarted()
        setUp()
        try {
            val func = this::class.memberFunctions.find { func -> func.name == name }
                ?: throw RuntimeException("Can't find method named '$name'")
            func.call(this)
        } catch (e: Exception) {
            result.testFailed()
        }
        tearDown()
        return result
    }
}

class TestResult(var runCount: Int = 0, var failureCount: Int = 0) {
    fun testStarted() {
        runCount += 1
    }

    fun testFailed() {
        failureCount += 1
    }

    fun summary() = "$runCount run, $failureCount failed"
}