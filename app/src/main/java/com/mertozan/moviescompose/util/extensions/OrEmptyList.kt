package com.mertozan.moviescompose.util.extensions

import com.mertozan.moviescompose.data.model.dto.Genres

fun List<Genres>?.orEmptyList() : List<Genres>{
    return this ?: emptyList()
}