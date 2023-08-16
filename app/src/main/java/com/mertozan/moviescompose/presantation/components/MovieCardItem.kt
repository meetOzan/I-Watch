package com.mertozan.moviescompose.presantation.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.mertozan.moviescompose.BuildConfig
import com.mertozan.moviescompose.R
import com.mertozan.moviescompose.ui.theme.Dark80
import com.mertozan.moviescompose.ui.theme.amazonEmberFamily
import com.mertozan.moviescompose.util.extensions.isLongerThan

@Composable
fun MovieItem(
    onCardClick: () -> Unit,
    posterPath: String,
    title: String,
    number: Int
) {

    var isFavorite by rememberSaveable {
        mutableStateOf(false)
    }

    val animateFavColor: Color by animateColorAsState(
        if (isFavorite) Color.Yellow else Color.White,
        label = stringResource(R.string.animated_color)
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
            .clickable(onClick = onCardClick)
            .background(Dark80)
    ) {
        Box {
            AsyncImage(
                model = "${BuildConfig.POSTER_BASE_PATH}${posterPath}",
                contentDescription = stringResource(R.string.movie_poster),
                modifier = Modifier
                    .padding(bottom = 2.dp)
                    .width(200.dp)
                    .height(300.dp),
                alignment = Alignment.Center
            )
        }
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "#$number",
                fontSize = 24.sp,
                fontFamily = amazonEmberFamily,
                modifier = Modifier.padding(start = 4.dp, bottom = 4.dp),
                color = Color.Gray,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.width(135.dp))
            Image(
                imageVector = Icons.Filled.Favorite,
                contentDescription = stringResource(R.string.add_fav),
                colorFilter = ColorFilter.tint(animateFavColor),
                modifier = Modifier
                    .size(28.dp)
                    .padding(bottom = 4.dp)
                    .clickable { isFavorite = !isFavorite },
                alignment = Alignment.TopStart
            )
        }
        Text(
            title.isLongerThan(),
            fontSize = 15.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 6.dp, start = 4.dp),
            color = Color.White,
            fontWeight = FontWeight.SemiBold
        )
    }
}