package com.mertozan.moviescompose.presantation.profile

import android.util.Log
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
    val user = _user.asStateFlow()

    init {
        getUser()
    }

    private fun getUser() {
        viewModelScope.launch {
            _user.value = repository.getUser()
            _user.value = _user.value.copy(
                fullName = userItem.name + " " + userItem.surname
            )
        }
    }

    fun signOut() {
        viewModelScope.launch {
            repository.signOut()
            _user.value.name = ""
        }
    }
}