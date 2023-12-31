package com.mertozan.moviescompose.presentation.entry.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mertozan.moviescompose.R
import com.mertozan.moviescompose.data.mapper.toUserItemToUserEntity
import com.mertozan.moviescompose.domain.model.UserModel
import com.mertozan.moviescompose.presentation.entry.viewmodel.EntryAction
import com.mertozan.moviescompose.presentation.home.viewmodel.HomeAction
import com.mertozan.moviescompose.presentation.main.components.CustomText
import com.mertozan.moviescompose.presentation.main.components.CustomTextField
import com.mertozan.moviescompose.presentation.theme.DarkYellow
import com.mertozan.moviescompose.presentation.theme.LightBlack
import com.mertozan.moviescompose.util.extensions.nameRegex

@Composable
fun ProfileInfoScreen(
    userModel: UserModel,
    onNavigate: () -> Unit,
    continueAction: (EntryAction) -> Unit,
    homeAction: (HomeAction) -> Unit,
) {

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightBlack),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        CustomText(
            text = stringResource(R.string.enter_your_name_and_surname_to_continue),
            fontWeight = FontWeight.Bold,
            fontSize = 28,
            color = DarkYellow,
            modifier = Modifier.fillMaxWidth(0.8f)
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
                    continueAction(EntryAction.ChangeItemName(it))
                }
            )
            CustomTextField(
                userModel.surname,
                stringResource(R.string.enter_your_surname),
                onChangeValue = {
                    continueAction(EntryAction.ChangeItemSurname(it))
                }
            )
            Spacer(modifier = Modifier.height(24.dp))
            ElevatedButton(
                onClick = {
                    if (userModel.name.isEmpty() || userModel.surname.isEmpty()) {
                        Toast.makeText(
                            context,
                            context.getString(
                                R.string.please_enter_user_information
                            ), Toast.LENGTH_SHORT
                        ).show()
                    } else if (userModel.name.nameRegex() || userModel.surname.nameRegex()) {
                        Toast.makeText(
                            context,
                            context.getString(R.string.please_enter_your_name_with_true_format),
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        continueAction(
                            EntryAction.CreateUserInFirebase(
                                name = userModel.name,
                                surname = userModel.surname,
                                watched = userModel.watched,
                            )
                        )
                        continueAction(
                            EntryAction.AddUserToLocal(
                                UserModel(
                                    name = userModel.name,
                                    surname = userModel.surname,
                                    watched = userModel.watched
                                ).toUserItemToUserEntity()
                            )
                        )
                        onNavigate()
                        Toast.makeText(
                            context,
                            context.getString(R.string.welcome_to_i_watch), Toast.LENGTH_SHORT
                        ).show()
                        homeAction(HomeAction.GetAllContents)
                    }
                },
                colors = ButtonDefaults.elevatedButtonColors(
                    containerColor = DarkYellow
                ),
                modifier = Modifier.width(150.dp)
            ) {
                CustomText(
                    text = stringResource(R.string.continue_to_app),
                    modifier = Modifier.padding(horizontal = 8.dp),
                    color = Color.Black
                )
            }
        }
    }
}