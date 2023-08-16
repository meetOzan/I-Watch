package com.mertozan.moviescompose.data.mapper

import com.mertozan.moviescompose.data.model.Movie
import com.mertozan.moviescompose.data.model.Series
import com.mertozan.moviescompose.domain.model.DetailItem

fun List<Movie>.moviesToList() : MutableList<DetailItem> {

    val detailList = mutableListOf<DetailItem>()

    for(i in 0..lastIndex){
       detailList.add(this[i].toMovieItem())
    }

    return detailList
}

fun List<Series>.seriesToList(): MutableList<DetailItem> {

    val detailList = mutableListOf<DetailItem>()

    for(i in 0..lastIndex){
        detailList.add(this[i].toSeriesItem())
    }

    return detailList
}