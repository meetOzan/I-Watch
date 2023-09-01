package com.mertozan.moviescompose.presantation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mertozan.moviescompose.domain.model.UserModel
import com.mertozan.moviescompose.domain.usecase.GetUserFromLocal
import com.mertozan.moviescompose.domain.usecase.SignOut
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getUserFromLocal: GetUserFromLocal,
    private val userSignOut: SignOut
) : ViewModel() {

    private val _user = MutableStateFlow(UserModel())
    val user = _user.asStateFlow()

    init {
        getUser()
    }

    private fun getUser() {
        viewModelScope.launch(Dispatchers.IO) {
            _user.value = getUserFromLocal.invoke()
            _user.value = _user.value.copy(
                fullName = _user.value.name + " " + _user.value.surname
            )
        }
    }

    fun signOut() {
        viewModelScope.launch(Dispatchers.IO) {
            userSignOut()
            _user.value.name = ""
        }
    }
}