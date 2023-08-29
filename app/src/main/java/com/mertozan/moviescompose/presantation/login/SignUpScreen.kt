package com.mertozan.moviescompose.presantation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import com.mertozan.moviescompose.R
import com.mertozan.moviescompose.presantation.components.CustomText
import com.mertozan.moviescompose.ui.theme.LightBlack

@Composable
fun SignUpScreen(
    onNavigate: () -> Unit
) {

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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightBlack),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        CustomText(
            text = stringResource(R.string.sign_up),
            fontWeight = FontWeight.Bold,
            fontSize = 28,
            color = Color.Yellow,
            modifier = Modifier.padding(end = 16.dp, top = 64.dp)
        )
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(bottom = 36.dp)
        ) {
            TextField(
                value = name,
                onValueChange = {
                    name = it
                },
                placeholder = { Text(stringResource(R.string.enter_your_name)) },
                modifier = Modifier
                    .padding(bottom = 16.dp),
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
                    .padding(bottom = 16.dp),
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
                    .padding(bottom = 16.dp),
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
                    .padding(bottom = 8.dp),
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
                onClick = onNavigate,
                colors = ButtonDefaults.elevatedButtonColors(
                    containerColor = Color.Yellow
                ),
                modifier = Modifier.width(150.dp)
            ) {
                CustomText(
                    text = stringResource(id = R.string.sign_up),
                    modifier = Modifier.padding(horizontal = 8.dp),
                    color = Color.Black
                )
            }
        }
    }
}