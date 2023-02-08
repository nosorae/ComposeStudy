package com.study.compose.tdd

open class Money(
    protected val amount: Int
) {
    override fun equals(other: Any?): Boolean {
        val money = other as Money
        return amount == money.amount
    }
}

class Dollor(
    amount: Int
) : Money(amount) {
    operator fun times(amount: Int): Dollor {
        return Dollor(this.amount * amount)
    }
}

class Franc(
    amount: Int
) : Money(amount) {
    operator fun times(amount: Int): Franc {
        return Franc(this.amount * amount)
    }
}

class MyTest(
    val amount: Int
)