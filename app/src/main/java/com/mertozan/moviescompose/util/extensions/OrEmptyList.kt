package com.mertozan.moviescompose.util.extensions

import com.mertozan.moviescompose.data.model.Genres

fun List<Genres>?.orEmptyList() : List<Genres>{
    return this ?: emptyList()
}