package com.study.compose.tdd


class Dollor(
    private var amount: Int
) {
    operator fun times(amount: Int): Dollor {
        return Dollor(this.amount * amount)
    }

    override fun equals(other: Any?): Boolean {
        val dollor = other as Dollor
        return amount == dollor.amount
    }
}

class MyTest(
    val amount: Int
)