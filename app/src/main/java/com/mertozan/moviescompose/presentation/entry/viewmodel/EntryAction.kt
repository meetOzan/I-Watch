package com.mertozan.moviescompose.presentation.entry.viewmodel

import com.mertozan.moviescompose.data.model.entity.UserEntity

sealed class EntryAction {
    data object TransferUserLocal : EntryAction()
    data object GetRowCount : EntryAction()
    data class ChangeItemName(val name: String) : EntryAction()
    data class ChangeItemSurname(val surname: String) : EntryAction()
    data class CreateUserInFirebase(
        val name: String,
        val surname: String,
        val watched: Int
    ) : EntryAction()
    data class AddUserToLocal(
        val userEntity: UserEntity
    ) : EntryAction()
}