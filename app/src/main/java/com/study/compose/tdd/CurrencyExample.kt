package com.study.compose.tdd


class Dollor(
    var amount: Int
) {
    operator fun times(amount: Int): Dollor {
        return Dollor(this.amount * amount)
    }
}
