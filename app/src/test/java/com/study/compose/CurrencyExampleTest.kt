package com.study.compose

import com.study.compose.tdd.Dollor
import org.junit.Test

class CurrencyExampleTest {
    @Test
    fun testMultiplication() {
        val five = Dollor(5)
        assert(10 == five.times(2).amount)
        five.times(3)
        assert(15 == five.times(3).amount)
    }
}