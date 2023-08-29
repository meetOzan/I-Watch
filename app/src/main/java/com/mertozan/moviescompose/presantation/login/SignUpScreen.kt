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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.mertozan.moviescompose.R
import com.mertozan.moviescompose.presantation.components.components.CustomText
import com.mertozan.moviescompose.presantation.components.components.CustomTextField
import com.mertozan.moviescompose.ui.theme.DarkYellow
import com.mertozan.moviescompose.ui.theme.LightBlack

@Composable
fun SignUpScreen(
    onNavigate: () -> Unit,
    viewModel: LoginViewModel
) {

    val userId = viewModel.userItem.collectAsState().value
    val userCurrent = viewModel.checkCurrentUser.collectAsState().value

    LaunchedEffect(userCurrent) {
        if (userCurrent) {
            onNavigate()
            viewModel.transferUserToLocal()
        }
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
            color = DarkYellow,
            modifier = Modifier.padding(end = 16.dp, top = 64.dp)
        )
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(bottom = 36.dp)
        ) {
            CustomTextField(
                userId.name,
                stringResource(R.string.enter_your_name),
                onChangeValue = {
                    viewModel.changeItemName(it)
                }
            )
            CustomTextField(
                userId.surname,
                stringResource(R.string.enter_your_surname),
                onChangeValue = {
                    viewModel.changeItemSurname(it)
                }
            )
            CustomTextField(
                userId.signUpEmail, stringResource(R.string.enter_your_e_mail),
                onChangeValue = {
                    viewModel.changeItemSignUpEmail(it)
                },
                keyboardType = KeyboardType.Email
            )
            CustomTextField(
                userId.signUpPassword, stringResource(R.string.enter_your_password),
                onChangeValue = {
                    viewModel.changeItemSignUpPassword(it)
                },
                visualTransformation = PasswordVisualTransformation(),
                keyboardType = KeyboardType.Password
            )
            Spacer(modifier = Modifier.height(24.dp))
            ElevatedButton(
                onClick = {
                    viewModel.createUserInFirebase(
                        name = userId.name,
                        surname = userId.surname,
                        email = userId.signUpEmail,
                        password = userId.signUpPassword,
                        watched = userId.watched
                    )
                    if (userCurrent) {
                        onNavigate()
                        viewModel.transferUserToLocal()
                    }
                },
                colors = ButtonDefaults.elevatedButtonColors(
                    containerColor = DarkYellow
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