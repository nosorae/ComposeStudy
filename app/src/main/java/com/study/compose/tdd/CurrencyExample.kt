package com.study.compose.tdd

open class Money(
    val amount: Int,
    private val currency: String
) : Expression {
    override fun equals(other: Any?): Boolean {
        val money = other as Money
        return amount == money.amount && currency == money.currency
    }
    operator fun times(amount: Int): Expression {
        return Money(this.amount * amount, currency)
    }

    override fun plus(money: Expression): Expression {
        return Sum(this, money)
    }

    override fun reduce(bank: Bank, to: String): Money {
        val rate = bank.rate(currency, to)
        return Money(amount / rate, to)
    }

    override fun toString(): String {
        return "$amount-$currency"
    }

    companion object {
        fun dollor(amount: Int) = Money(amount, "USD")
        fun franc(amount: Int) = Money(amount, "CHF")
    }
}

interface Expression {
    fun reduce(bank: Bank, to: String): Money
    operator fun plus(addend: Expression): Expression
}

class Bank {
    private val hashMap: HashMap<Pair<String, String>, Int> = hashMapOf()

    fun reduce(source: Expression, to: String): Money {
        return source.reduce(this, to)
    }


    fun addRate(from: String, to: String, rate: Int) {
        hashMap[Pair(from, to)] = rate
    }

    fun rate(from: String, to: String): Int {
        if (from == to) return 1
        return hashMap[Pair(from, to)] ?: throw Exception("No rate info")
    }
}

class Sum(
    private val augend: Expression,
    private val addend: Expression
) : Expression {
    override fun reduce(bank: Bank, to: String): Money {
        val amount = augend.reduce(bank, to).amount + addend.reduce(bank, to).amount
        return Money(amount, to)
    }

    override fun plus(addend: Expression): Expression {
        return Sum(this, addend)
    }
}


class MyTest(
    val amount: Int
)