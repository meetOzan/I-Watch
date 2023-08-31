package com.mertozan.moviescompose.util.extensions

fun Boolean?.orFalse(): Boolean {
    return this ?: false
}

fun Float?.orZeroFloat(): Float {
    return this ?: 0f
}

fun Int?.orZero(): Int {
    return this ?: 0
}