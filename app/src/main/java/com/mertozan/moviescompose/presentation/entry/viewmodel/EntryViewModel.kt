package com.mertozan.moviescompose.presentation.entry.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.mertozan.moviescompose.data.model.dto.User
import com.mertozan.moviescompose.data.model.entity.UserEntity
import com.mertozan.moviescompose.data.remote.response.NetworkResponse
import com.mertozan.moviescompose.data.repository.CollectionName.COLLECTION_NAME
import com.mertozan.moviescompose.domain.model.UserModel
import com.mertozan.moviescompose.domain.usecase.AddUserToLocal
import com.mertozan.moviescompose.domain.usecase.GetRowCount
import com.mertozan.moviescompose.domain.usecase.TransferUserToLocal
import com.mertozan.moviescompose.util.extensions.nameRegex
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class EntryViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseFirestore: FirebaseFirestore,
    private val transferUserToLocal: TransferUserToLocal,
    private val addUserToLocal: AddUserToLocal,
    private val getRowCount: GetRowCount
) : ViewModel() {

    private var _entryUiState = MutableStateFlow(EntryUiState())
    val entryUiState = _entryUiState.asStateFlow()

    val userItem = MutableStateFlow(UserModel())

    init {
        checkRowCount()
    }

    fun onAction(action: EntryAction) {
        when (action) {
            is EntryAction.TransferUserLocal -> transferUserLocal()
            is EntryAction.ChangeItemName -> changeItemName(action.name)
            is EntryAction.ChangeItemSurname -> changeItemSurname(action.surname)
            is EntryAction.GetRowCount -> checkRowCount()
            is EntryAction.AddUserToLocal -> addUser(userEntity = action.userEntity)
            is EntryAction.CreateUserInFirebase -> createUserInFirebase(
                name = action.name,
                surname = action.surname,
                watched = action.watched
            )
        }
    }

    private fun addUser(userEntity: UserEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            addUserToLocal(userEntity)
        }
    }

    private fun checkRowCount() {
        viewModelScope.launch {
            _entryUiState.value = _entryUiState.value.copy(isLoading = true)
            when (val response = getRowCount()) {
                is NetworkResponse.Error -> {
                    _entryUiState.value =
                        _entryUiState.value.copy(exceptionMessage = response.error.message.toString())
                    _entryUiState.value =
                        _entryUiState.value.copy(isLoading = false)
                }

                is NetworkResponse.Success -> {
                    _entryUiState.value =
                        _entryUiState.value.copy(rowCount = response.data)
                    _entryUiState.value =
                        _entryUiState.value.copy(isLoading = false)
                }
            }
        }
    }

    private fun transferUserLocal() {
        viewModelScope.launch(Dispatchers.IO) {
            transferUserToLocal()
        }
    }

    private fun createUserInFirebase(
        name: String,
        surname: String,
        watched: Int
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                if (name.nameRegex() || surname.nameRegex()) {
                    saveUser(name, surname, watched)
                } else {
                    _entryUiState.value =
                        _entryUiState.value.copy(exceptionMessage = "Please fill blanks")
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _entryUiState.value =
                        _entryUiState.value.copy(exceptionMessage = e.message.orEmpty())
                }
            }
        }
    }

    private fun saveUser(
        name: String,
        surname: String,
        watched: Int
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            firebaseFirestore.collection(COLLECTION_NAME)
                .document(firebaseAuth.uid.toString())
                .set(User(name, surname, watched))
                .addOnFailureListener {
                    _entryUiState.value =
                        _entryUiState.value.copy(exceptionMessage = "Wrong E-Mail or Password")
                }
        }
    }

    private fun changeItemName(name: String) {
        userItem.value = userItem.value.copy(name = name)
    }

    private fun changeItemSurname(surname: String) {
        userItem.value = userItem.value.copy(surname = surname)
    }
}