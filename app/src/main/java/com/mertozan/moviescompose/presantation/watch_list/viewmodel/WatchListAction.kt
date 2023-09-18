package com.mertozan.moviescompose.presantation.watch_list.viewmodel

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
}
