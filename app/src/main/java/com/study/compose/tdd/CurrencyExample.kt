package com.study.compose.tdd

import java.util.Hashtable

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

    override fun reduce(bank: Bank, to: String): Money {
        val rate = bank.rate(currency, to)
        return Money(amount / rate, to)
    }

    companion object {
        fun dollor(amount: Int) = Money(amount, "USD")
        fun franc(amount: Int) = Money(amount, "CHF")
    }
}

interface Expression {
    fun reduce(bank: Bank, to: String): Money
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
        return hashMap[Pair(from, to)] ?: throw Exception("No rate info")
    }
}

class Sum(
    private val augend: Money,
    private val addend: Money
) : Expression {
    override fun reduce(bank: Bank, to: String): Money {
        return Money(augend.amount + addend.amount, to)
    }
}


class MyTest(
    val amount: Int
)