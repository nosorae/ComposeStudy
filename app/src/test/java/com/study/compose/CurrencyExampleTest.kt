package com.study.compose

import com.study.compose.tdd.Dollor
import org.junit.Test

class CurrencyExampleTest {
    @Test
    fun testMultiplication() {
        val five = Dollor(5)
        five.times(2)
        assert(10 == five.amount)
        five.times(3)
        assert(15 == five.amount)
    }
}