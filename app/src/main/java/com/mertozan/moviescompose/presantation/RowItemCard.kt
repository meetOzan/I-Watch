package com.mertozan.moviescompose.presantation

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.mertozan.moviescompose.data.model.Movie
import com.mertozan.moviescompose.ui.theme.Dark80

@Composable
fun RowItem(
    onCardClick: () -> Unit,
    movie: Movie,
    number: Int
) {

    var isFavorite by rememberSaveable {
        mutableStateOf(false)
    }

    val animateFavColor: Color by animateColorAsState(
        if (isFavorite) Color.Red else Color.White,
        label = "animated_color"
    )

    Column(
        modifier = Modifier
            .padding(8.dp)
            .clip(MaterialTheme.shapes.medium)
            .border(
                width = 0.5.dp,
                shape = MaterialTheme.shapes.medium,
                color = Color.Yellow
            )
            .clickable { onCardClick }
            .background(Dark80)
    ) {
        Box {
            AsyncImage(
                model = ("https://image.tmdb.org/t/p/original/${movie.posterPath}"),
                contentDescription = "movie_poster",
                modifier = Modifier
                    .padding(bottom = 2.dp)
                    .fillMaxSize(0.25f),
                alignment = Alignment.Center
            )
            Image(
                imageVector = Icons.Filled.Favorite,
                contentDescription = "add_fav",
                colorFilter = ColorFilter.tint(animateFavColor),
                modifier = Modifier
                    .size(36.dp)
                    .padding(5.dp)
                    .clickable { isFavorite = !isFavorite }
                    .background(Color.Gray.copy(alpha = 0.5f)),
                alignment = Alignment.TopStart
            )
        }
        Text(
            "#$number",
            fontSize = 24.sp,
            modifier = Modifier.padding(start = 4.dp, bottom = 4.dp),
            color = Color.Gray,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            movie.title.isLongerThan(),
            fontSize = 14.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 6.dp, start = 4.dp),
            color = Color.White,
            fontWeight = FontWeight.SemiBold
        )
    }
}

fun String.isLongerThan(): String {
    return if (this.length > 11) {
        var newText = ""
        for (i in 0..11) {
            newText += this[i]
        }
        "$newText..."
    } else {
        this
    }
}