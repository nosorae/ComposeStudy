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

abstract class TestCase(open val name: String) {
    open fun setUp() {}
    open fun tearDown() {}

    operator fun invoke(result: TestResult): TestResult {
        result.testStarted()
        setUp()
        try {
            val func = this::class.memberFunctions.find { func -> func.name == name }
                ?: throw RuntimeException("Can't find method named '$name'")
            func.call(this)
        } catch (e: Exception) {
            result.testFailed()
        } finally {
            tearDown()
        }
        return result
    }

    operator fun invoke() {
        setUp()
        val func = this::class.memberFunctions.find { func -> func.name == name }
            ?: throw RuntimeException("Can't find method named '$name'")
        func.call(this)
        tearDown()
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

class TestSuite<T : TestCase> {
    private val tests = mutableListOf<T>()

    fun add(test: T) {
        tests.add(test)
    }

    operator fun invoke(result: TestResult) {
        tests.forEach { test ->
            test(result)
        }
    }
}

class TestCaseTest(override val name: String) : TestCase(name) {
    lateinit var testResult: TestResult

    override fun setUp() {
        testResult = TestResult()
    }

    fun testTemplatedMethod() {
        val test = WasRun(name = "testMethod")
        test(testResult)
        assertEquals("setUp testMethod tearDown ", test.log)
    }

    fun testResult() {
        val test = WasRun(name = "testMethod")
        val result = test(testResult)
        assertEquals("1 run, 0 failed", result.summary())
    }

    fun testSuite() {
        val suite = TestSuite<WasRun>()
        suite.add(WasRun("testMethod"))
        suite.add(WasRun("testBrokenMethod"))
        suite(testResult)
        assertEquals("2 run, 1 failed", testResult.summary())
    }
}