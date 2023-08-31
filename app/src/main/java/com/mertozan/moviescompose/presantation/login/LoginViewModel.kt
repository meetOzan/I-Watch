package com.mertozan.moviescompose.presantation.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.mertozan.moviescompose.common.Constants.COLLECTION_NAME
import com.mertozan.moviescompose.data.model.User
import com.mertozan.moviescompose.data.repository.MovieRepository
import com.mertozan.moviescompose.domain.model.UserItem
import com.mertozan.moviescompose.util.extensions.emailRegex
import com.mertozan.moviescompose.util.extensions.passwordRegex
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseFirestore: FirebaseFirestore,
    private val repository: MovieRepository
) : ViewModel() {

    private var _checkCurrentUser = MutableStateFlow(false)
    val checkCurrentUser = _checkCurrentUser.asStateFlow()

    private var _exceptionMessage = MutableStateFlow("")
    val exceptionMessage = _exceptionMessage.asStateFlow()

    val userItem = MutableStateFlow(UserItem())

    init {
        checkLogged()
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

    fun transferUserToLocal(){
        viewModelScope.launch {
            repository.transferUserToLocal()
        }
    }

    fun createUserInFirebase(
        name: String,
        surname: String,
        email: String,
        password: String,
        watched: Int
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                if (email.emailRegex() || name.isNotEmpty() || surname.isNotEmpty() || password.passwordRegex()) {
                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener {
                            saveUser(name, surname, email, password,watched)
                        }
                        .addOnFailureListener {
                            _exceptionMessage.value = "User can't created"
                        }
                } else {
                    _exceptionMessage.value = "Please fill blanks"
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Log.e("Catch Exception: ", e.message.orEmpty())
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
        firebaseFirestore.collection(COLLECTION_NAME)
            .document(firebaseAuth.uid.toString())
            .set(User(name, surname, email, password,watched))
            .addOnCompleteListener {
                checkLogged()
                _exceptionMessage.value = "User Saved"
            }
            .addOnFailureListener {
                _exceptionMessage.value = "Wrong E-Mail or Password"
            }
    }

    fun signInFirebase(
        email: String, password: String
    ) {
        CoroutineScope(Dispatchers.IO).launch {
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

    fun changeItemName(name: String) {
        userItem.value = userItem.value.copy(name = name)
    }

    fun changeItemSurname(surname: String) {
        userItem.value = userItem.value.copy(surname = surname)
    }

    fun changeItemSignInEmail(email: String) {
        userItem.value = userItem.value.copy(signInEmail = email)
    }

    fun changeItemSignUpEmail(email: String) {
        userItem.value = userItem.value.copy(signUpEmail = email)
    }

    fun changeItemSignInPassword(password: String) {
        userItem.value = userItem.value.copy(signInPassword = password)
    }

    fun changeItemSignUpPassword(password: String) {
        userItem.value = userItem.value.copy(signUpPassword = password)
    }
}