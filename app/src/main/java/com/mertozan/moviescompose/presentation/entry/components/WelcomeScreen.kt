package com.mertozan.moviescompose.presentation.entry.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.sharp.Add
import androidx.compose.material.icons.sharp.Favorite
import androidx.compose.material.icons.sharp.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mertozan.moviescompose.R
import com.mertozan.moviescompose.presentation.main.components.CustomText
import com.mertozan.moviescompose.presentation.theme.DarkWhite80
import com.mertozan.moviescompose.presentation.theme.DarkYellow
import com.mertozan.moviescompose.presentation.theme.LightBlack

@Composable
fun WelcomeScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightBlack)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(top = 76.dp)
                .weight(2f)
        ) {
            Image(
                painterResource(id = R.drawable.splash_logo),
                contentDescription = stringResource(R.string.splash_icon),
                colorFilter = ColorFilter.tint(Color.White),
                modifier = Modifier
                    .size(100.dp)
                    .padding(bottom = 8.dp)
            )
            CustomText(
                text = stringResource(R.string.i_watch),
                fontWeight = FontWeight.Bold,
                fontSize = 46,
                color = DarkYellow
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.weight(1.5f)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
                    .background(color = DarkYellow, shape = MaterialTheme.shapes.large)
            ) {
                Image(
                    imageVector = Icons.Sharp.Search,
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(LightBlack),
                    alignment = Alignment.Center,
                    modifier = Modifier
                        .size(40.dp)
                        .alpha(0.8f)
                )
                CustomText(
                    text = stringResource(R.string.discover_the_movies_you_love),
                    fontSize = 20,
                    fontWeight = FontWeight.SemiBold,
                    color = LightBlack,
                    modifier = Modifier
                        .fillMaxWidth(0.75f)
                        .padding(vertical = 8.dp)
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
                    .background(
                        color = DarkYellow,
                        shape = MaterialTheme.shapes.large
                    )
            ) {
                Image(
                    imageVector = Icons.Sharp.Add,
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(LightBlack),
                    alignment = Alignment.Center,
                    modifier = Modifier.size(40.dp)
                )
                CustomText(
                    text = stringResource(R.string.save_them_watchlist),
                    fontSize = 20,
                    fontWeight = FontWeight.SemiBold,
                    color = LightBlack,
                    modifier = Modifier
                        .fillMaxWidth(0.75f)
                        .padding(vertical = 8.dp)
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
                    .background(
                        color = DarkYellow,
                        shape = MaterialTheme.shapes.large
                    )
            ) {
                Image(
                    imageVector = Icons.Sharp.Favorite,
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(LightBlack),
                    alignment = Alignment.Center,
                    modifier = Modifier.size(40.dp)
                )
                CustomText(
                    text = stringResource(R.string.like_what_you_like),
                    fontSize = 20,
                    fontWeight = FontWeight.SemiBold,
                    color = LightBlack,
                    modifier = Modifier
                        .fillMaxWidth(0.75f)
                        .padding(vertical = 8.dp)
                )
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(bottom = 24.dp)
        ) {
            Image(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = stringResource(R.string.swipe_left),
                Modifier,
                alignment = Alignment.Center,
                colorFilter = ColorFilter.tint(DarkWhite80)
            )
            CustomText(text = stringResource(R.string.swipe_left_now), fontSize = 20)
        }
    }
}