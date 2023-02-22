package com.study.compose

import com.study.compose.tdd.WasRun
import org.junit.Ignore
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
}