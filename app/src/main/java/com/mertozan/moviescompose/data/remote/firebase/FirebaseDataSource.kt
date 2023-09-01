package com.mertozan.moviescompose.data.remote.firebase

import android.content.res.Resources
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.mertozan.moviescompose.data.repository.CollectionName
import com.mertozan.moviescompose.domain.model.UserModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class FirebaseDataSource @Inject constructor(
    private val db: FirebaseFirestore,
    private val auth: FirebaseAuth
) {
    suspend fun getUser(): UserModel {
        return CoroutineScope(Dispatchers.IO).async {
            try {
                db.collection(CollectionName.COLLECTION_NAME)
                    .document(auth.currentUser?.uid.toString())
                db.collection(CollectionName.COLLECTION_NAME)
                    .document(auth.currentUser?.uid.toString())
                    .get().await().toObject<UserModel>() ?: UserModel()
            } catch (e: Exception) {
                UserModel()
            }
        }.await()
    }

    suspend fun getUserFromNetwork() = suspendCoroutine { continuation ->
        db.collection("users").document(auth.currentUser?.uid.toString())
            .get().addOnSuccessListener {
                if (it.exists()) {
                    val user = it.toObject<UserModel>()!!
                    continuation.resumeWith(Result.success(user))
                } else {
                    continuation.resumeWithException(Resources.NotFoundException())
                }
            }.addOnFailureListener {
                continuation.resumeWithException(it)
            }
    }

    fun signOut() {
        CoroutineScope(Dispatchers.IO).launch {
            auth.signOut()
        }
    }
}