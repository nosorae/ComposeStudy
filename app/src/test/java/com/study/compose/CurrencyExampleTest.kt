package com.study.compose

import com.study.compose.tdd.Dollor
import com.study.compose.tdd.Franc
import com.study.compose.tdd.Money
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
    @Test
    fun testMultiplicationFranc() {
        val five = Franc(5)
        assert(Franc(10) == five.times(2))
        five.times(3)
        assert(Franc(15) == five.times(3))
    }

    @Test
    fun testBetweenDollorAndFranc() {
//        assert(Franc(5) == Dollor(5))
    }

    @Test
    fun testMultiplicationByFactory() {
        val five = Money.dollor(5)
        assert(Dollor(10) == five.times(2))

    }
}
// 임시 변수를 없애면, 일련의 오퍼레이션이 아니라 참인 명제에 대한 단언들이므로 우리의 의도를 더 명확하게 이야기해준다.