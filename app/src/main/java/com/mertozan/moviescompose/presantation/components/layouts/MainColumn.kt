package com.mertozan.moviescompose.presantation.components.layouts

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mertozan.moviescompose.R
import com.mertozan.moviescompose.domain.model.DetailItem
import com.mertozan.moviescompose.presantation.components.components.CustomText
import com.mertozan.moviescompose.presantation.components.items.MainColumnItem
import com.mertozan.moviescompose.ui.theme.DarkYellow
import com.mertozan.moviescompose.ui.theme.LightBlack

@Composable
fun MainColumn(
    list: List<DetailItem>,
    type: String,
    listType: String,
    onToDetailClick: (Int, String, String) -> Unit,
    onToContentListClick : (String) -> Unit,
    title: String
) {
    Row(
        modifier = Modifier.padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            modifier = Modifier.weight(1f)
        ) {
            Divider(
                modifier = Modifier
                    .height(21.dp)
                    .width(3.dp),
                color = DarkYellow
            )
            CustomText(
                text = title,
                fontSize = 20,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
        CustomText(
            text = stringResource(R.string.see_more),
            fontSize = 18,
            fontWeight = FontWeight.Normal,
            color = Color.White,
            modifier = Modifier.clickable {
                onToContentListClick(type)
            }
        )
    }
    LazyColumn(
        modifier = Modifier
            .background(LightBlack)
            .height(250.dp)
    ) {
        itemsIndexed(list) { index, content ->
            if (index < 15) {
                MainColumnItem(
                    title = content.title,
                    number = (list.indexOf(content)) + 1,
                    onNavigate = {
                        onToDetailClick(content.id, type, listType)
                    }
                )
            }
        }
    }
}