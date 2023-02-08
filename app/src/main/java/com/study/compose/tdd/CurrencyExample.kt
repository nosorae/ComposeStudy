package com.study.compose.tdd

class Dollor(
    private val amount: Int
) {
    operator fun times(amount: Int): Dollor {
        return Dollor(this.amount * amount)
    }

    override fun equals(other: Any?): Boolean {
        val dollor = other as Dollor
        return amount == dollor.amount
    }
}

class Franc(
    private val amount: Int
) {
    operator fun times(amount: Int): Franc {
        return Franc(this.amount * amount)
    }

    override fun equals(other: Any?): Boolean {
        val dollor = other as Franc
        return amount == dollor.amount
    }
}

class MyTest(
    val amount: Int
)