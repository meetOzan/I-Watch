package com.mertozan.moviescompose.util.extensions

fun String.isLongerThan(): String {
    return if (this.length > 22) {
        var newText = ""
        for (i in 0..22) {
            newText += this[i]
        }
        "$newText..."
    } else {
        this
    }
}