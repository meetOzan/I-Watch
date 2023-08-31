package com.mertozan.moviescompose.presantation.login

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
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
        name: String,
        surname: String,
        email: String,
        password: String
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                if (email.isNotEmpty() || name.isNotEmpty() || surname.isNotEmpty() || password.isNotEmpty()) {
                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener {
                            saveUser(name, surname, email, password)
                        }
                        .addOnFailureListener {
                            Log.e(
                                "Firebase Firestore ERROR:",
                                "${
                                    UserItem(
                                        name,
                                        surname,
                                        email,
                                        password
                                    )
                                } can't logged."
                            )
                        }
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

    private fun saveUser(
        name: String,
        surname: String,
        email: String,
        password: String
    ) {
        firebaseFirestore.collection("users")
            .document(firebaseAuth.uid.toString())
            .set(UserItem(name, surname, email, password))
            .addOnCompleteListener {
                checkLogged()
                Log.e(
                    "Firebase Auth SUCCESS:",
                    "${
                        UserItem(
                            name,
                            surname,
                            email,
                            password
                        )
                    } can't logged."
                )
            }
            .addOnFailureListener {
                Log.e(
                    "Firebase Auth ERROR:",
                    "${
                        UserItem(
                            name,
                            surname,
                            email,
                            password
                        )
                    } can't logged."
                )
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