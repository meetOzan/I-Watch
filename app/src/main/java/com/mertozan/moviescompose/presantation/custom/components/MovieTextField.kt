package com.mertozan.moviescompose.presantation.custom.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.mertozan.moviescompose.ui.theme.DarkWhite80
import com.mertozan.moviescompose.ui.theme.DarkYellow
import com.mertozan.moviescompose.ui.theme.LightBlack

@Composable
fun CustomTextField(
    givenValue: String,
    placeHolder: String,
    onChangeValue: (String) -> Unit,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardType: KeyboardType = KeyboardType.Text
) {

    TextField(
        value = givenValue,
        onValueChange = onChangeValue,
        maxLines = 1,
        placeholder = {
            CustomText(
                placeHolder,
                color = DarkWhite80.copy(alpha = 0.7f)
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 36.dp),
        shape = MaterialTheme.shapes.medium,
        colors = TextFieldDefaults.colors(
            focusedTextColor = LightBlack,
            unfocusedTextColor = DarkYellow,
            focusedContainerColor = DarkWhite80,
            unfocusedContainerColor = Color.Black,
            focusedIndicatorColor = LightBlack,
            unfocusedIndicatorColor = LightBlack
        ),
        visualTransformation = visualTransformation,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType)
    )
}