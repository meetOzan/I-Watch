package com.mertozan.moviescompose.presantation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mertozan.moviescompose.data.repository.MovieRepository
import com.mertozan.moviescompose.domain.model.UserItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    private val _user = MutableStateFlow(UserItem())
    private val user = _user.asStateFlow()

    private val _userFullName = MutableStateFlow("")
    val userFullName = _userFullName.asStateFlow()

    init {
        getUser()
        _userFullName.value = user.value.name + " " + user.value.surname
    }

    private fun getUser() {
        viewModelScope.launch {
            repository.getUser(_user)
        }
    }

    fun signOut() {
        viewModelScope.launch {
            repository.signOut()
        }
    }
}