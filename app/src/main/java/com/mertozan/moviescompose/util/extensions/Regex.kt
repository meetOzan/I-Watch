package com.mertozan.moviescompose.util.extensions

import java.util.regex.Pattern

fun String.emailRegex() : Boolean{
    val pattern = Pattern.compile(
        "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}\$")
    return pattern.matcher(this).matches()
}

fun String.passwordRegex() : Boolean{
    val pattern = Pattern.compile(
        "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&+=])(?=\\\\S+\$).{4,}\$")
    return pattern.matcher(this).matches()
}