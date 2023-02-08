package com.study.compose.tdd

open class Money(
    protected val amount: Int
) {
    override fun equals(other: Any?): Boolean {
        val money = other as Money
        println("$javaClass / ${money.javaClass}")
        return amount == money.amount && javaClass == money.javaClass
    }

    companion object {
        fun dollor(amount: Int) = Dollor(amount)
        fun franc(amount: Int) = Franc(amount)
    }
}

class Dollor(
    amount: Int
) : Money(amount) {
    operator fun times(amount: Int): Money {
        return Dollor(this.amount * amount)
    }
}

class Franc(
    amount: Int
) : Money(amount) {
    operator fun times(amount: Int): Money {
        return Franc(this.amount * amount)
    }
}

class MyTest(
    val amount: Int
)