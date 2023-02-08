package com.study.compose.tdd

open class Money(
    private val amount: Int,
    private val currency: String
) : Expression {
    override fun equals(other: Any?): Boolean {
        val money = other as Money
        return amount == money.amount && currency == money.currency
    }
    operator fun times(amount: Int): Money {
        return Money(this.amount * amount, currency)
    }

    operator fun plus(money: Money): Expression {
        return Money(this.amount + money.amount, currency)
    }

    companion object {
        fun dollor(amount: Int) = Money(amount, "USD")
        fun franc(amount: Int) = Money(amount, "CHF")
    }
}

interface Expression

class Bank {
    fun reduce(source: Expression, to: String): Money {
        return Money.dollor(10)
    }
}


class MyTest(
    val amount: Int
)