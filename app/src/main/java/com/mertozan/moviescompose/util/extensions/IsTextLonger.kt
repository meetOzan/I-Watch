package com.mertozan.moviescompose.util.extensions

fun String.isLongerThan(much: Int): String {
    return if (this.length > much) {
        var newText = ""
        for (i in 0..much) {
            newText += this[i]
        }
        "$newText..."
    } else {
        this
    }
}