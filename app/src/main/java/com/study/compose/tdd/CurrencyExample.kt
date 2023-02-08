package com.study.compose.tdd

open class Money(
    protected val amount: Int,
    protected val currency: String
) {
    override fun equals(other: Any?): Boolean {
        val money = other as Money
        return amount == money.amount && currency == money.currency
    }

    companion object {
        fun dollor(amount: Int) = Dollor(amount, "USD")
        fun franc(amount: Int) = Franc(amount, "CHF")
    }
}

class Dollor(
    amount: Int,
    currency: String
) : Money(amount, currency) {
    operator fun times(amount: Int): Money {
        return Money(this.amount * amount, currency)
    }
}

class Franc(
    amount: Int,
    currency: String
) : Money(amount, currency) {
    operator fun times(amount: Int): Money {
        return franc(this.amount * amount)
    }
}

class MyTest(
    val amount: Int
)