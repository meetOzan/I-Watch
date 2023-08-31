package com.mertozan.moviescompose.util.extensions

fun String.isLongerThan(what: Int): String {
    return if (this.length > what) {
        var newText = ""
        for (i in 0..what) {
            newText += this[i]
        }
        "$newText..."
    } else {
        this
    }
}