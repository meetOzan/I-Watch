package com.mertozan.moviescompose.infrastructure.provider

import androidx.annotation.StringRes

fun interface StringResourceProvider {

    fun getString(@StringRes id: Int) : String

}