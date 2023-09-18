package com.mertozan.moviescompose.presantation.profile.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mertozan.moviescompose.data.remote.response.NetworkResponse
import com.mertozan.moviescompose.domain.usecase.GetAllWatchedListContents
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
    private val getUserNetwork: GetUserFromLocal,
    private val userSignOut: SignOut,
    private val getAllWatchedListContents: GetAllWatchedListContents
) : ViewModel() {

    private val _profileUiState = MutableStateFlow(ProfileUiState())
    val profileUiState = _profileUiState.asStateFlow()

    init {
        getUser()
        getProfileWatchedContent()
    }

    private fun getUser() {
        viewModelScope.launch(Dispatchers.IO) {
            getUserFromLocal()
            _profileUiState.value.user = _profileUiState.value.user.copy(
                fullName = _profileUiState.value.user.name + " " + _profileUiState.value.user.surname
            )
        }
    }

    fun signOut() {
        viewModelScope.launch(Dispatchers.IO) {
            userSignOut()
            _profileUiState.value.user.name = ""
        }
    }

    private suspend fun getUserFromLocal() {
        _profileUiState.value = profileUiState.value.copy(isLoading = true)
        when (val response = getUserNetwork()) {
            is NetworkResponse.Error -> {
                _profileUiState.value.errorMessage = response.error
                _profileUiState.value = _profileUiState.value.copy(isLoading = false)
            }

            is NetworkResponse.Success -> {
                _profileUiState.value.user = response.data
                _profileUiState.value = _profileUiState.value.copy(isLoading = false)
            }
        }
    }

    private fun getProfileWatchedContent() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val response = getAllWatchedListContents()) {
                is NetworkResponse.Error -> {
                    _profileUiState.value =
                        _profileUiState.value.copy(errorMessage = response.error)
                    _profileUiState.value =
                        _profileUiState.value.copy(isLoading = false)
                }

                is NetworkResponse.Success -> {
                    _profileUiState.value =
                        _profileUiState.value.copy(watchCount = response.data.size)
                    _profileUiState.value =
                        _profileUiState.value.copy(isLoading = false)
                }
            }
        }
    }
}