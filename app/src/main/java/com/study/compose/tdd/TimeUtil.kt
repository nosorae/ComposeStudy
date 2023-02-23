package com.study.compose.tdd

fun Long.mapToStringForAnalytics(): String {
    val totalSec = this / 1000
    val totalMin = totalSec / 60
    val totalHour = totalMin / 60

    val sec = totalSec % 60
    val min = totalMin % 60
    val hour = totalHour % 24
    val days = totalHour / 24
    return "%d일 %02d : %02d : %02d".format(
        days,
        hour,
        min,
        sec
    )
}

fun mapMillisToStringForAnalytics(time: Long): String {
    val sec = (time / 1000) % 60
    val min = (time / 1000 / 60) % 60
    val hour = (time / 1000 / 60 / 60) % 24
    val days = time / 1000 / 60 / 60 / 24
    return "%d일 %02d : %02d : %02d".format(
        days,
        hour,
        min,
        sec
    )
}