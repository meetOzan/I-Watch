package com.mertozan.moviescompose.util.extensions

fun String.isLongerThan(): String {
    return if (this.length > 11) {
        var newText = ""
        for (i in 0..11) {
            newText += this[i]
        }
        "$newText..."
    } else {
        this
    }
}