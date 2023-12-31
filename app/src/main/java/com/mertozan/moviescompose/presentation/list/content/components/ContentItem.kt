package com.mertozan.moviescompose.presentation.list.content.components

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mertozan.moviescompose.R
import com.mertozan.moviescompose.domain.model.ContentModel
import com.mertozan.moviescompose.presentation.main.components.CustomAsyncImage
import com.mertozan.moviescompose.presentation.main.components.CustomText
import com.mertozan.moviescompose.presentation.navigation.DetailScreen
import com.mertozan.moviescompose.presentation.theme.Dark80
import com.mertozan.moviescompose.presentation.theme.DarkWhite80
import com.mertozan.moviescompose.presentation.theme.DarkYellow
import com.mertozan.moviescompose.util.enums.ContentType
import com.mertozan.moviescompose.util.enums.ListType
import com.mertozan.moviescompose.util.extensions.isLongerThan

@Composable
fun ContentItem(
    content: ContentModel,
    type: String,
    navController: NavController,
) {

    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                if (type == ContentType.FAVORITE_CONTENTS.name) {
                    Toast
                        .makeText(
                            context,
                            context.getText(R.string.details_inaccessible_from_there),
                            Toast.LENGTH_SHORT
                        )
                        .show()
                } else {
                    navController.navigate(
                        DetailScreen.navigateWithArgs(
                            content.id,
                            type,
                            ListType.TOP_RATED.name
                        )
                    )
                }
            }
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
                    model = content.posterPath,
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
                        text = content.title.isLongerThan(18),
                        fontSize = 24,
                        color = DarkWhite80,
                        FontWeight.SemiBold,
                        modifier = Modifier
                            .padding(top = 8.dp, bottom = 16.dp)
                    )
                    CustomText(
                        text = content.overview.isLongerThan(100),
                        fontSize = 14,
                        color = DarkWhite80,
                        FontWeight.SemiBold,
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    Row(
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        CustomText(
                            text = content.voteAverage,
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

    /*if (openDialog) {
        CustomAlertDialog(
            title = stringResource(R.string.don_t_you_like_it_anymore),
            body = stringResource(R.string.are_you_sure_you_want_to_remove_the_content),
            positiveButtonName = stringResource(R.string.yes),
            negativeButtonName = stringResource(R.string.no_i_think_i_still_like_it),
            onPositiveAction = {
                listAction(
                    ListAction.UpdateFavoriteState(
                        id = content.id,
                        isFavorite = content.isFavorite,
                        type = content.type
                    )
                )
                openDialog = !openDialog
            },
            onNegativeAction = { openDialog = !openDialog },
            onDismissClick = { openDialog = !openDialog }
        )
    }*/
}