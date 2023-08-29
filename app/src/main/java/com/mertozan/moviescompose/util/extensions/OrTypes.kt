package com.mertozan.moviescompose.util.extensions

import com.mertozan.moviescompose.data.model.Genres

fun Boolean?.orFalse(): Boolean {
    return this ?: false
}

fun Float?.orZeroFloat() : Float{
    return this ?: 0f
}

fun Int?.orZero() : Int{
    return this ?: 0
}

fun List<Genres>?.orEmptyList() : List<Genres>{
    return this ?: emptyList()
}