package com.mertozan.moviescompose.presentation.auth.signIn

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.mertozan.moviescompose.R
import com.mertozan.moviescompose.domain.model.UserModel
import com.mertozan.moviescompose.presentation.auth.viewmodel.AuthAction
import com.mertozan.moviescompose.presentation.main.components.CustomText
import com.mertozan.moviescompose.presentation.main.components.CustomTextField
import com.mertozan.moviescompose.presentation.theme.DarkWhite80
import com.mertozan.moviescompose.presentation.theme.DarkYellow
import com.mertozan.moviescompose.presentation.theme.LightBlack

@Composable
fun SignInScreen(
    userModel: UserModel,
    userCurrent: Boolean,
    toastMessage: String,
    onNavigate: () -> Unit,
    signInOnAction: (AuthAction) -> Unit
) {

    val context = LocalContext.current

    val email = userModel.signInEmail
    val password = userModel.signInPassword

    LaunchedEffect(userCurrent) {
        if (userCurrent) {
            signInOnAction(AuthAction.TransferUserLocal)
            onNavigate()
        }
    }

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
        ) {
            Spacer(modifier = Modifier.height(75.dp))
            CustomText(
                text = stringResource(R.string.welcome_to_movieapp),
                fontWeight = FontWeight.Bold,
                fontSize = 30,
                color = DarkYellow
            )
            Spacer(modifier = Modifier.height(25.dp))
            CustomText(
                text = stringResource(R.string.sign_in),
                fontWeight = FontWeight.Bold,
                fontSize = 28,
                color = DarkYellow,
                modifier = Modifier.padding(end = 16.dp)
            )
            Spacer(modifier = Modifier.height(100.dp))
            CustomTextField(
                email,
                placeHolder = stringResource(id = R.string.enter_your_e_mail),
                onChangeValue = {
                    signInOnAction(AuthAction.ChangeItemSignInEmail(it))
                },
                keyboardType = KeyboardType.Email
            )
            CustomTextField(
                password,
                placeHolder = stringResource(id = R.string.enter_your_password),
                onChangeValue = {
                    signInOnAction(AuthAction.ChangeItemSignInPassword(it))
                },
                visualTransformation = PasswordVisualTransformation(),
                keyboardType = KeyboardType.Password
            )
            ElevatedButton(
                onClick = {
                    signInOnAction(AuthAction.SignInFirebase(email, password))
                    if (userCurrent) {
                        onNavigate()
                        signInOnAction(AuthAction.TransferUserLocal)
                    } else {
                        Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show()
                    }
                },
                colors = ButtonDefaults.elevatedButtonColors(
                    containerColor = DarkYellow
                ),
                modifier = Modifier
                    .width(150.dp)
                    .padding(top = 24.dp)
            ) {
                CustomText(
                    text = stringResource(id = R.string.sign_in),
                    modifier = Modifier.padding(horizontal = 8.dp),
                    color = Color.Black,
                )
            }
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            CustomText(
                text = stringResource(R.string.you_don_t_have_an_account),
                fontSize = 18,
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(8.dp)
            ) {
                Image(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = stringResource(R.string.swipe_left),
                    Modifier,
                    alignment = Alignment.Center,
                    colorFilter = ColorFilter.tint(DarkWhite80)
                )
                CustomText(text = stringResource(R.string.swipe_left_now), fontSize = 16)
            }
        }
    }
}