package com.mertozan.moviescompose.presentation.watch_list.viewmodel

sealed class WatchListAction {
    data class TransferToWatched(
        val id: Int,
        val isInWatchList: Boolean,
        val type: String,
        val listType: String
    ) : WatchListAction()

    data class RemoveFromWatchList(
        val id: Int,
        val isInWatchList: Boolean,
        val type: String,
        val listType: String
    ) : WatchListAction()

    data object GetAllContents : WatchListAction()

    data class RemoveContentFromWatched(
        val id: Int,
        val isWatched: Boolean,
        val type: String,
        val listType: String
    ) : WatchListAction()
}
