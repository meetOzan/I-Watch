package com.mertozan.moviescompose.presantation.auth.viewmodel

sealed class AuthAction {
    data object TransferUserLocal : AuthAction()
    data class CreateUserInFirebase(
        val name: String,
        val surname: String,
        val email: String,
        val password: String,
        val watched: Int
    ) : AuthAction()
    data class SignInFirebase(
        val email: String, val password: String
    ) : AuthAction()
    data class ChangeItemName(val name: String) : AuthAction()
    data class ChangeItemSurname(val surname: String) : AuthAction()
    data class ChangeItemSignInEmail(val email: String) : AuthAction()
    data class ChangeItemSignUpEmail(val email: String) : AuthAction()
    data class ChangeItemSignInPassword(val password: String) : AuthAction()
    data class ChangeItemSignUpPassword(val password: String) : AuthAction()
}