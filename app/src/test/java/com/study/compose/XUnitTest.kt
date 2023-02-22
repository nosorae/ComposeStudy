package com.study.compose

import com.study.compose.tdd.TestCaseTest
import com.study.compose.tdd.WasRun
import org.junit.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class XUnitTest {
    @Test
    fun testWasRun() {
        val wasRun = WasRun(name = "testMethod")
        assertFalse { wasRun.wasRun }
        wasRun()
        assertTrue { wasRun.wasRun }
    }

    @Test
    fun testTestRunning() {
        TestCaseTest("testRunning")()
    }

    @Test
    fun testSetUP() {
        TestCaseTest("testSetUp")()
    }
}