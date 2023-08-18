package com.mertozan.moviescompose.presantation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mertozan.moviescompose.R
import com.mertozan.moviescompose.ui.theme.LightBlack
import com.mertozan.moviescompose.ui.theme.amazonEmberFamily

@Composable
fun SignUpScreen() {

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var name by remember {
        mutableStateOf("")
    }

    var surname by remember {
        mutableStateOf("")
    }

    // Sıgn Up Screen
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightBlack),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(180.dp))
        Text(
            text = stringResource(R.string.sign_up),
            fontFamily = amazonEmberFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            color = Color.Yellow,
            modifier = Modifier.padding(end = 16.dp)
        )
        Spacer(modifier = Modifier.height(150.dp))
        TextField(
            value = name,
            onValueChange = {
                name = it
            },
            placeholder = { Text(stringResource(R.string.enter_your_name)) },
            modifier = Modifier
                .padding(bottom = 16.dp)
                .border(
                    width = 0.4.dp,
                    shape = MaterialTheme.shapes.medium,
                    color = Color.Yellow
                ),
            shape = MaterialTheme.shapes.medium,
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.Yellow,
                focusedContainerColor = Color.DarkGray,
                unfocusedContainerColor = Color.Black,
                focusedIndicatorColor = LightBlack,
                unfocusedIndicatorColor = LightBlack
            )
        )
        TextField(
            value = surname,
            onValueChange = {
                surname = it
            },
            maxLines = 1,
            placeholder = { Text(stringResource(R.string.enter_your_surname)) },
            modifier = Modifier
                .padding(bottom = 16.dp)
                .border(
                    width = 0.4.dp,
                    shape = MaterialTheme.shapes.medium,
                    color = Color.Yellow
                ),
            shape = MaterialTheme.shapes.medium,
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.Yellow,
                focusedContainerColor = Color.DarkGray,
                unfocusedContainerColor = Color.Black,
                focusedIndicatorColor = LightBlack,
                unfocusedIndicatorColor = LightBlack
            )
        )
        TextField(
            value = email,
            onValueChange = {
                email = it
            },
            maxLines = 1,
            placeholder = { Text(stringResource(R.string.enter_your_e_mail)) },
            modifier = Modifier
                .padding(bottom = 16.dp)
                .border(
                    width = 0.4.dp,
                    shape = MaterialTheme.shapes.medium,
                    color = Color.Yellow
                ),
            shape = MaterialTheme.shapes.medium,
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.Yellow,
                focusedContainerColor = Color.DarkGray,
                unfocusedContainerColor = Color.Black,
                focusedIndicatorColor = LightBlack,
                unfocusedIndicatorColor = LightBlack
            )
        )
        TextField(
            value = password,
            onValueChange = {
                password = it
            },
            maxLines = 1,
            placeholder = { Text(stringResource(R.string.enter_your_password)) },
            modifier = Modifier
                .padding(bottom = 8.dp)
                .border(
                    width = 0.4.dp,
                    shape = MaterialTheme.shapes.medium,
                    color = Color.Yellow
                ),
            shape = MaterialTheme.shapes.medium,
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.Yellow,
                focusedContainerColor = Color.DarkGray,
                unfocusedContainerColor = Color.Black,
                focusedIndicatorColor = LightBlack,
                unfocusedIndicatorColor = LightBlack
            )
        )
        Spacer(modifier = Modifier.height(24.dp))
        ElevatedButton(
            onClick = {}, colors = ButtonDefaults.elevatedButtonColors(
                containerColor = Color.Yellow
            ), modifier = Modifier.width(150.dp)
        ) {


            Text(
                text = stringResource(id = R.string.sign_up),
                modifier = Modifier.padding(horizontal = 8.dp),
                color = Color.Black
            )
        }
    }

}