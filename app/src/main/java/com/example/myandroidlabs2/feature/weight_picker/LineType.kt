package com.example.myandroidlabs2.feature.weight_picker

sealed class LineType {
    object Normal : LineType()
    object FiveStep : LineType()
    object TenStep : LineType()
}
