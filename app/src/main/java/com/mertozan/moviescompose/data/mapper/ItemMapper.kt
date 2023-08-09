package com.mertozan.moviescompose.data.mapper

import com.mertozan.moviescompose.data.dto.MovieDto
import com.mertozan.moviescompose.data.dto.SeriesDto
import com.mertozan.moviescompose.domain.model.MovieItem
import com.mertozan.moviescompose.domain.model.SeriesItem

class ItemMapper {

    fun MovieDto.toMovieItem(): MovieItem {
        return MovieItem(
            title = this.title,
            popularity = this.popularity,
            adult = this.adult,
            genresDto = this.genres,
            posterPath = this.posterPath,
            releaseDate = this.releaseDate,
            overview = this.overview
        )
    }

    fun SeriesDto.toSeriesItem(): SeriesItem {
        return SeriesItem(
            name = this.name,
            popularity = this.popularity,
            firstAirDate = this.firstAirDate,
            genresDto = this.genres,
            posterPath = this.posterPath,
            originalLanguage = this.originalLanguage,
            overview = this.overview
        )
    }

}
