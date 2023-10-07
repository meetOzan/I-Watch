package com.mertozan.moviescompose.presentation.profile.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mertozan.moviescompose.data.remote.response.NetworkResponse
import com.mertozan.moviescompose.domain.model.UserModel
import com.mertozan.moviescompose.domain.usecase.DeleteUserFromLocale
import com.mertozan.moviescompose.domain.usecase.GetAllWatchedListContents
import com.mertozan.moviescompose.domain.usecase.GetSingleLocalUser
import com.mertozan.moviescompose.domain.usecase.GetUserFromLocal
import com.mertozan.moviescompose.domain.usecase.TransferNetworkToLocale
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val deleteUserFromLocale: DeleteUserFromLocale,
    private val transferNetworkToLocale: TransferNetworkToLocale,
    private val getSingleLocalUser: GetSingleLocalUser,
    private val getAllWatchedListContents: GetAllWatchedListContents
) : ViewModel() {

    private val _profileUiState = MutableStateFlow(ProfileUiState())
    val profileUiState = _profileUiState.asStateFlow()

    init {
        getUserFromLocal()
        getProfileWatchedContent()
    }

    fun onAction(action: ProfileAction) {
        when (action) {
            is ProfileAction.SignOut -> signOut(userModel = action.userModel)
        }
    }

    private fun signOut(userModel: UserModel) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteUserFromLocale(userModel)
            transferNetworkToLocale()
        }
    }

    private fun getUserFromLocal() {
        viewModelScope.launch {
            _profileUiState.value = profileUiState.value.copy(isLoading = true)
            when (val response = getSingleLocalUser()) {
                is NetworkResponse.Error -> {
                    _profileUiState.value.errorMessage = response.error
                    _profileUiState.value = _profileUiState.value.copy(isLoading = false)
                }

                is NetworkResponse.Success -> {
                    _profileUiState.value.user = _profileUiState.value.user.copy(
                        fullName = response.data.name + " " + response.data.surname
                    )
                    _profileUiState.value = _profileUiState.value.copy(isLoading = false)
                }
            }
        }
    }

    private fun getProfileWatchedContent() {
        _profileUiState.value = profileUiState.value.copy(isLoading = true)
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