package com.mertozan.moviescompose.infrastructure.stringResource

import androidx.annotation.StringRes

fun interface StringResourceProvider {

    fun getString(@StringRes id: Int) : String

}