package com.study.compose

import com.study.compose.tdd.mapToStringForAnalytics
import org.junit.Test
import kotlin.test.assertEquals

class TimeUtilTest {
    @Test
    fun testDay() {
        // https://www.epochconverter.com/
        val day10 = 1676008360000L
        val day15 = 1676440360000L
        val diff = day15 - day10
        val result = diff.mapToStringForAnalytics().substringBefore(" ")
        assertEquals("5일", result)
    }
}