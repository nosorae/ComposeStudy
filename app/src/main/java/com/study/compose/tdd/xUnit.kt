package com.study.compose.tdd

class WasRun(var wasRun: Boolean = false, name: String) {
    fun testMethod() {
        wasRun = true
    }
}