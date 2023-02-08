package com.study.compose.tdd

open class Money(
    val amount: Int,
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

    override fun reduce(to: String): Money {
        return this
    }

    companion object {
        fun dollor(amount: Int) = Money(amount, "USD")
        fun franc(amount: Int) = Money(amount, "CHF")
    }
}

interface Expression {
    fun reduce(to: String): Money
}

class Bank {
    fun reduce(source: Expression, to: String): Money {
        return source.reduce(to)
    }
}

class Sum(
    private val augend: Money,
    private val addend: Money
) : Expression {
    override fun reduce(to: String): Money {
        return Money(augend.amount + addend.amount, to)
    }
}


class MyTest(
    val amount: Int
)