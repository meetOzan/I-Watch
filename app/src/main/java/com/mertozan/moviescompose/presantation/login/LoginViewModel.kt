package com.mertozan.moviescompose.presantation.login

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.mertozan.moviescompose.data.model.User
import com.mertozan.moviescompose.domain.model.UserItem
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
    private val firebaseFirestore: FirebaseFirestore
) : ViewModel() {

    private var _checkCurrentUser = MutableStateFlow(false)
    val checkCurrentUser = _checkCurrentUser.asStateFlow()

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

    fun createUserInFirebase(
        user: User
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                if (user.email.isNotEmpty() || user.name.isNotEmpty() || user.surname.isNotEmpty() || user.password.isNotEmpty()) {
                    firebaseAuth.createUserWithEmailAndPassword(user.email, user.password)
                        .addOnCompleteListener {
                            firebaseFirestore.collection("users")
                                .document(firebaseAuth.uid.toString())
                                .set(UserItem(user.name, user.surname, user.email, user.password))
                                .addOnCompleteListener {
                                    Log.e(
                                        "Firebase Auth msg:",
                                        "${
                                            UserItem(
                                                user.name,
                                                user.surname,
                                                user.email,
                                                user.password
                                            )
                                        } logged."
                                    )
                                    checkLogged()
                                }
                                .addOnFailureListener {
                                    Log.e(
                                        "Firebase Auth ERROR:",
                                        "${
                                            UserItem(
                                                user.name,
                                                user.surname,
                                                user.email,
                                                user.password
                                            )
                                        } can't logged."
                                    )
                                }
                        }.await()
                } else {
                    Log.e("Please fill blanks", "")
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Log.e("Catch Exception: ", e.message.orEmpty())
                }
            }
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
                            Log.e(
                                "Firebase Auth msg:",
                                "$email $password successfully signed."
                            )
                            checkLogged()
                        }
                        .addOnFailureListener {
                            Log.e(
                                "Firebase Auth ERROR:",
                                "$email $password can't signed."
                            )
                        }.await()
                } else {
                    Log.e(
                        "Email and Password Error",
                        "$email and $password are empty."
                    )
                }
            } catch (e: Exception) {
                Log.e(
                    "Sign In Exception",
                    e.message.orEmpty()
                )
            }
        }
    }

    fun changeItemName(it: String) {
        userItem.value = userItem.value.copy(name = it)
    }

    fun changeItemSurname(it: String) {
        userItem.value = userItem.value.copy(surname = it)
    }

    fun changeItemSignInEmail(it: String) {
        userItem.value = userItem.value.copy(signInEmail = it)
    }

    fun changeItemSignUpEmail(it: String) {
        userItem.value = userItem.value.copy(signUpEmail = it)
    }

    fun changeItemSignInPassword(it: String) {
        userItem.value = userItem.value.copy(signInPassword = it)
    }


    fun changeItemSignUpPassword(it: String) {
        userItem.value = userItem.value.copy(signUpPassword = it)
    }
}