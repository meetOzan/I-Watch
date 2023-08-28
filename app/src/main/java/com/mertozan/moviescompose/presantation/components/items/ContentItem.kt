package com.mertozan.moviescompose.presantation.components.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mertozan.moviescompose.R
import com.mertozan.moviescompose.presantation.components.components.CustomAsyncImage
import com.mertozan.moviescompose.presantation.components.components.CustomText
import com.mertozan.moviescompose.ui.theme.Dark80
import com.mertozan.moviescompose.ui.theme.DarkWhite80
import com.mertozan.moviescompose.ui.theme.DarkYellow
import com.mertozan.moviescompose.util.extensions.isLongerThan

@Composable
fun ContentItem(
    title: String,
    posterPath: String,
    voteAverage: String,
    detail: String,
    onNavigate: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onNavigate() }
            .padding(horizontal = 12.dp, vertical = 8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Dark80
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                CustomAsyncImage(
                    model = posterPath,
                    contentDescription = stringResource(R.string.custom_content_item_poster),
                    modifier = Modifier
                        .padding(8.dp)
                        .size(
                            height = 150.dp,
                            width = 100.dp
                        )
                        .clip(MaterialTheme.shapes.medium),
                    alignment = Alignment.Center
                )
                Column(
                    modifier = Modifier
                        .align(Alignment.Top)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.Start
                ) {
                    CustomText(
                        text = title.isLongerThan(18),
                        fontSize = 24,
                        color = DarkWhite80,
                        FontWeight.SemiBold,
                        modifier = Modifier
                            .padding(top = 16.dp)
                    )
                    CustomText(
                        text = detail.isLongerThan(60),
                        fontSize = 14,
                        color = DarkWhite80,
                        FontWeight.SemiBold,
                        modifier = Modifier
                            .padding(end = 24.dp, top = 16.dp, bottom = 16.dp)
                    )
                    Row(
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        CustomText(
                            text = voteAverage,
                            fontSize = 16,
                            color = Color.White,
                            modifier = Modifier.padding(4.dp)
                        )
                        Image(
                            imageVector = Icons.Filled.Star,
                            contentDescription = stringResource(R.string.liked),
                            colorFilter = ColorFilter.tint(
                                DarkYellow
                            ),
                            modifier = Modifier.padding(end = 10.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewOfContent() {
    ContentItem(
        title = "Forrest Gump",
        posterPath = "/arw2vcBveWOVZr6pxd9XTd1TdQa.jpg",
        "8.7",
        "nofdnsjaogndogndfognadoskngodfnsgoasnognosadnggojnsdkogndsaognojkadsnagojndsagojnsadojgnjsoadgnfdoasjnfogkndsag"
    ) {}
}