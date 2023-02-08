package com.study.compose.tdd

open class Money(
    private val amount: Int,
    private val currency: String
) {
    override fun equals(other: Any?): Boolean {
        val money = other as Money
        return amount == money.amount && currency == money.currency
    }
    operator fun times(amount: Int): Money {
        return Money(this.amount * amount, currency)
    }

    companion object {
        fun dollor(amount: Int) = Money(amount, "USD")
        fun franc(amount: Int) = Money(amount, "CHF")
    }
}

class MyTest(
    val amount: Int
)