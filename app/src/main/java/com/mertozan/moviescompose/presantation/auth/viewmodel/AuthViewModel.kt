package com.mertozan.moviescompose.presantation.auth.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.mertozan.moviescompose.data.model.dto.User
import com.mertozan.moviescompose.data.repository.CollectionName.COLLECTION_NAME
import com.mertozan.moviescompose.domain.model.UserModel
import com.mertozan.moviescompose.domain.usecase.TransferUserToLocal
import com.mertozan.moviescompose.util.extensions.emailRegex
import com.mertozan.moviescompose.util.extensions.passwordRegex
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseFirestore: FirebaseFirestore,
    private val transferUserToLocal: TransferUserToLocal
) : ViewModel() {

    private var _checkCurrentUser = MutableStateFlow(false)
    val checkCurrentUser = _checkCurrentUser.asStateFlow()

    private var _exceptionMessage = MutableStateFlow("")
    val exceptionMessage = _exceptionMessage.asStateFlow()

    private var _uiState = MutableStateFlow(AuthUiState())
    val uiState = _uiState.asStateFlow()

    val userItem = MutableStateFlow(UserModel())

    init {
        checkLogged()
    }

    fun onAction(action: AuthAction) {
        when (action) {
            is AuthAction.TransferUserLocal -> transferUserLocal()
            is AuthAction.ChangeItemName -> changeItemName(action.name)
            is AuthAction.ChangeItemSignInEmail -> changeItemSignInEmail(action.email)
            is AuthAction.ChangeItemSignInPassword -> changeItemSignInPassword(action.password)
            is AuthAction.ChangeItemSignUpPassword -> changeItemSignUpPassword(action.password)
            is AuthAction.ChangeItemSignUpEmail -> changeItemSignUpEmail(action.email)
            is AuthAction.ChangeItemSurname -> changeItemSurname(action.surname)
            is AuthAction.CreateUserInFirebase -> createUserInFirebase(
                name = action.name,
                surname =  action.surname,
                email = action.email,
                password = action.password,
                watched = action.watched
            )
            is AuthAction.SignInFirebase -> signInFirebase(
                email = action.email,
                password = action.password,
            )
        }
    }

    private fun checkLogged() {
        if (firebaseAuth.currentUser != null) {
            firebaseAuth.currentUser?.reload()
            _checkCurrentUser.value = true
        } else {
            firebaseAuth.currentUser?.reload()
            _checkCurrentUser.value = false
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
        email: String,
        password: String,
        watched: Int
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                if (email.emailRegex() || name.isNotEmpty() || surname.isNotEmpty() || password.passwordRegex()) {
                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener {
                            saveUser(name, surname, email, password, watched)
                        }
                        .addOnFailureListener {
                            _exceptionMessage.value = "User can't created"
                        }
                } else {
                    _exceptionMessage.value = "Please fill blanks"
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _exceptionMessage.value = e.message.orEmpty()
                }
            }
        }
    }

    private fun saveUser(
        name: String,
        surname: String,
        email: String,
        password: String,
        watched: Int
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            firebaseFirestore.collection(COLLECTION_NAME)
                .document(firebaseAuth.uid.toString())
                .set(User(name, surname, email, password, watched))
                .addOnCompleteListener {
                    checkLogged()
                    _exceptionMessage.value = "User Saved"
                }
                .addOnFailureListener {
                    _exceptionMessage.value = "Wrong E-Mail or Password"
                }
        }
    }

    private fun signInFirebase(
        email: String, password: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                if (email.isNotEmpty() && password.isNotEmpty()) {
                    firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnSuccessListener {
                            checkLogged()
                            _exceptionMessage.value = "Login successful"
                        }
                        .addOnFailureListener {
                            _exceptionMessage.value = "Wrong E-Mail or Password"
                        }.await()
                } else {
                    _exceptionMessage.value = "Please fill blanks"
                }
            } catch (e: Exception) {
                _exceptionMessage.value = e.message.orEmpty()
            }
        }
    }

    private fun changeItemName(name: String) {
        userItem.value = userItem.value.copy(name = name)
    }

    private fun changeItemSurname(surname: String) {
        userItem.value = userItem.value.copy(surname = surname)
    }

    private fun changeItemSignInEmail(email: String) {
        userItem.value = userItem.value.copy(signInEmail = email)
    }

    private fun changeItemSignUpEmail(email: String) {
        userItem.value = userItem.value.copy(signUpEmail = email)
    }

    private fun changeItemSignInPassword(password: String) {
        userItem.value = userItem.value.copy(signInPassword = password)
    }

    private fun changeItemSignUpPassword(password: String) {
        userItem.value = userItem.value.copy(signUpPassword = password)
    }
}