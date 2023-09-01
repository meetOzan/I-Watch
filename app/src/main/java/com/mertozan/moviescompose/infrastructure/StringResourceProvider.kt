package com.mertozan.moviescompose.infrastructure

import androidx.annotation.StringRes

fun interface StringResourceProvider {

    fun getString(@StringRes id: Int) : String

}