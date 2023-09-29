package com.mertozan.moviescompose.presentation.auth.signUp

import android.widget.Toast
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.mertozan.moviescompose.R
import com.mertozan.moviescompose.domain.model.UserModel
import com.mertozan.moviescompose.presentation.auth.viewmodel.AuthAction
import com.mertozan.moviescompose.presentation.main_components.CustomText
import com.mertozan.moviescompose.presentation.main_components.CustomTextField
import com.mertozan.moviescompose.presentation.theme.DarkYellow
import com.mertozan.moviescompose.presentation.theme.LightBlack

@Composable
fun SignUpScreen(
    userModel: UserModel,
    userCurrent: Boolean,
    toastMessage: String,
    onNavigate: () -> Unit,
    signUpOnAction: (AuthAction) -> Unit
) {

    val context = LocalContext.current

    LaunchedEffect(userCurrent) {
        if (userCurrent) {
            onNavigate()
            signUpOnAction(AuthAction.TransferUserLocal)
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
                userModel.name,
                stringResource(R.string.enter_your_name),
                onChangeValue = {
                    signUpOnAction(AuthAction.ChangeItemName(it))
                }
            )
            CustomTextField(
                userModel.surname,
                stringResource(R.string.enter_your_surname),
                onChangeValue = {
                    signUpOnAction(AuthAction.ChangeItemSurname(it))
                }
            )
            CustomTextField(
                userModel.signUpEmail, stringResource(R.string.enter_your_e_mail),
                onChangeValue = {
                    signUpOnAction(AuthAction.ChangeItemSignUpEmail(it))
                },
                keyboardType = KeyboardType.Email
            )
            CustomTextField(
                userModel.signUpPassword, stringResource(R.string.enter_your_password),
                onChangeValue = {
                    signUpOnAction(AuthAction.ChangeItemSignUpPassword(it))
                },
                visualTransformation = PasswordVisualTransformation(),
                keyboardType = KeyboardType.Password
            )
            Spacer(modifier = Modifier.height(24.dp))
            ElevatedButton(
                onClick = {
                    signUpOnAction(
                        AuthAction.CreateUserInFirebase(
                            name = userModel.name,
                            surname = userModel.surname,
                            email = userModel.signUpEmail,
                            password = userModel.signUpPassword,
                            watched = userModel.watched
                        )
                    )
                    if (userCurrent) {
                        onNavigate()
                        signUpOnAction(AuthAction.TransferUserLocal)
                        Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show()
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