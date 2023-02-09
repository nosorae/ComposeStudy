package com.study.compose

import com.study.compose.tdd.Bank
import com.study.compose.tdd.Expression
import com.study.compose.tdd.Money
import com.study.compose.tdd.Sum
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse

class CurrencyExampleTest {
//    @Test
//    fun testMultiplication() {
//        val five = Dollor(5)
//        assert(Dollor(10) == five.times(2))
//        five.times(3)
//        assert(Dollor(15) == five.times(3))
//
//        assert(Dollor(5) == Dollor(5))
////        assert(MyTest(5) == MyTest(5))
//    }
//    @Test
//    fun testMultiplicationFranc() {
//        val five = Franc(5)
//        assert(Franc(10) == five.times(2))
//        five.times(3)
//        assert(Franc(15) == five.times(3))
//    }
//
//    @Test
//    fun testBetweenDollorAndFranc() {
////        assert(Franc(5).equals(Dollor(5)))
//    }

    @Test
    fun testMultiplicationByFactory() {
        val five = Money.dollor(5)
        assert(Money.dollor(10) == five.times(2))
//        assert(Money.dollor(10).equals(Money.franc(10)))
    }

    @Test
    fun test9() {
        val five = Money.dollor(5)
        assert(Money.dollor(10) == five * 2)
        assert(Money.franc(10) == Money.franc(5) * 2)
        assert(Money.franc(10) == Money(2, "CHF") * 5)
    }

    @Test
    fun testSimpleAddition() {
        val sum: Expression = Money.dollor(5) + Money.dollor(5)
        val bank = Bank()
        val reduced = bank.reduce(sum, "USD")
        assert(reduced == Money.dollor(10))
    }

    @Test
    fun testReduceSum() {
        val sum = Sum(Money.dollor(3), Money.dollor(4))
        val bank = Bank()
        val result = bank.reduce(sum, "USD")
        assert(Money.dollor(7) == result)
    }

    @Test
    fun testHashMap() {
        val bank = Bank()
        bank.addRate("A", "B", 3)
        assert(3 == bank.rate("A", "B"))
    }

    @Test
    fun testEqualCurrnecyRate() {
        assert(1 == Bank().rate("USD", "USD"))
    }

    @Test
    fun testMixedAddition() {
        val fiveBucks: Expression = Money.dollor(5)
        val tenFranc: Expression = Money.franc(10)
        val bank = Bank()
        bank.addRate("CHF", "USD", 2)
        val result = bank.reduce((fiveBucks + tenFranc) ?: throw Exception("null"), "USD")
        assertEquals(Money.dollor(10), result)
    }

    @Test
    fun testPlusMoney() {
        val fiveBucks: Expression = Money.dollor(5)
        val tenFranc: Expression = Money.franc(10)
        val bank = Bank()
        bank.addRate("CHF", "USD", 2)
        val sum: Expression = (Sum(fiveBucks, tenFranc) + fiveBucks)
        val result = bank.reduce(sum, "USD")
        assertEquals(Money.dollor(15), result)
        assertEquals(Money.dollor(15), bank.reduce(Sum(Sum(fiveBucks, tenFranc), fiveBucks), "USD"))
    }

    @Test
    fun testSumTimes() {
        val fiveBucks: Expression = Money.dollor(5)
        val tenFranc: Expression = Money.franc(10)
        val bank = Bank()
        bank.addRate("CHF", "USD", 2)
        val sum: Expression = Sum(fiveBucks, tenFranc) * 2
        val result = bank.reduce(sum, "USD")
        assertEquals(Money.dollor(20), result)
    }

    @Test
    fun testPlusSameCurrencyReturnsMoney() {
        val sum: Expression = Money.dollor(1) + Money.dollor(1)

        assertFalse { sum is Money }
    }
}
// 임시 변수를 없애면, 일련의 오퍼레이션이 아니라 참인 명제에 대한 단언들이므로 우리의 의도를 더 명확하게 이야기해준다.
