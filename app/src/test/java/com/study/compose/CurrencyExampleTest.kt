package com.study.compose

import com.study.compose.tdd.Dollor
import org.junit.Test

class CurrencyExampleTest {
    @Test
    fun testMultiplication() {
        val five = Dollor(5)
        assert(Dollor(10) == five.times(2))
        five.times(3)
        assert(Dollor(15) == five.times(3))

        assert(Dollor(5) == Dollor(5))
//        assert(MyTest(5) == MyTest(5))
    }
}