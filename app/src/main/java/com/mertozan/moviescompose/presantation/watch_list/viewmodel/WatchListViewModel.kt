package com.mertozan.moviescompose.presantation.watch_list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mertozan.moviescompose.data.remote.response.NetworkResponse
import com.mertozan.moviescompose.domain.usecase.GetAllInWatchListContents
import com.mertozan.moviescompose.domain.usecase.GetAllWatchedListContents
import com.mertozan.moviescompose.domain.usecase.UpdateMovieIsInWatch
import com.mertozan.moviescompose.domain.usecase.UpdateMovieIsWatched
import com.mertozan.moviescompose.domain.usecase.UpdateSeriesIsInWatch
import com.mertozan.moviescompose.domain.usecase.UpdateSeriesIsWatched
import com.mertozan.moviescompose.domain.usecase.UpdateTopMovieIsInWatch
import com.mertozan.moviescompose.domain.usecase.UpdateTopMovieIsWatched
import com.mertozan.moviescompose.domain.usecase.UpdateTopSeriesIsInWatch
import com.mertozan.moviescompose.domain.usecase.UpdateTopSeriesIsWatched
import com.mertozan.moviescompose.domain.usecase.UpdateUserWatchState
import com.mertozan.moviescompose.util.enums.ContentType
import com.mertozan.moviescompose.util.enums.ListType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WatchListViewModel @Inject constructor(
    private val getAllWatchedListContents: GetAllWatchedListContents,
    private val getAllInWatchListContents: GetAllInWatchListContents,
    private val updateMovieIsInWatch: UpdateMovieIsInWatch,
    private val updateTopMovieIsInWatch: UpdateTopMovieIsInWatch,
    private val updateSeriesIsInWatched: UpdateSeriesIsInWatch,
    private val updateTopSeriesIsInWatch: UpdateTopSeriesIsInWatch,
    private val updateMovieWatched: UpdateMovieIsWatched,
    private val updateTopMovieWatched: UpdateTopMovieIsWatched,
    private val updateSeriesWatched: UpdateSeriesIsWatched,
    private val updateTopSeriesWatch: UpdateTopSeriesIsWatched,
    private val updateUserWatchState: UpdateUserWatchState,
) : ViewModel() {

    private val _watchListUiState = MutableStateFlow(WatchListUiState())
    val watchListUiState = _watchListUiState.asStateFlow()

    init {
        getWatchedContentList()
        getIsInWatchedContentList()
    }

    fun onAction(action: WatchListAction) {
        when (action) {
            is WatchListAction.TransferToWatched -> transferToWatched(
                action.id,
                action.isInWatchList,
                action.listType,
                action.type
            )
            is WatchListAction.RemoveFromWatchList -> removeFromWatchList(
                action.id,
                action.isInWatchList,
                action.listType,
                action.type
            )
            is WatchListAction.GetAllContents -> {
                getWatchedContentList()
                getIsInWatchedContentList()
            }
        }
    }

    private fun transferToWatched(id: Int, isInWatch: Boolean, listType: String, type: String) {
        when (listType) {
            ListType.POPULAR.name -> when (type) {
                ContentType.SERIES.name -> {
                    updateSeriesIsInWatched(id, isInWatch)
                    updateSeriesWatched(id, !isInWatch)
                    getIsInWatchedContentList()
                }

                ContentType.MOVIE.name -> {
                    updateMovieIsInWatch(id, isInWatch)
                    updateMovieWatched(id, !isInWatch)
                    getIsInWatchedContentList()
                }
            }

            ListType.TOP_RATED.name -> when (type) {
                ContentType.SERIES.name -> {
                    updateTopSeriesIsInWatch(id, isInWatch)
                    updateTopSeriesWatch(id, !isInWatch)
                    getIsInWatchedContentList()
                }

                ContentType.MOVIE.name -> {
                    updateTopMovieIsInWatch(id, isInWatch)
                    updateTopMovieWatched(id, !isInWatch)
                    getIsInWatchedContentList()
                }
            }
        }
    }

    private fun removeFromWatchList(id: Int, isInWatch: Boolean, listType: String, type: String) {
        when (listType) {
            ListType.POPULAR.name -> when (type) {
                ContentType.SERIES.name -> updateSeriesIsInWatched(id, isInWatch)
                ContentType.MOVIE.name -> updateMovieIsInWatch(id, isInWatch)
            }

            ListType.TOP_RATED.name -> when (type) {
                ContentType.SERIES.name -> updateTopSeriesIsInWatch(id, isInWatch)
                ContentType.MOVIE.name -> updateTopMovieIsInWatch(id, isInWatch)
            }
        }
    }

    private fun getWatchedContentList() {
        viewModelScope.launch(Dispatchers.IO) {
            _watchListUiState.value = _watchListUiState.value.copy(isWatchedIsLoading = true)
            when (val response = getAllWatchedListContents()) {
                is NetworkResponse.Error -> {
                    _watchListUiState.value =
                        _watchListUiState.value.copy(isWatchedErrorMessage = response.error)
                    _watchListUiState.value =
                        _watchListUiState.value.copy(isWatchedIsLoading = false)
                }

                is NetworkResponse.Success -> {
                    _watchListUiState.value =
                        _watchListUiState.value.copy(isWatchedList = response.data)
                    _watchListUiState.value =
                        _watchListUiState.value.copy(isWatchedIsLoading = false)
                    updateUserWatchState(response.data.size)
                }
            }
        }
    }

    private fun getIsInWatchedContentList() {
        viewModelScope.launch(Dispatchers.IO) {
            _watchListUiState.value = _watchListUiState.value.copy(isInWatchedIsLoading = true)
            when (val response = getAllInWatchListContents()) {
                is NetworkResponse.Error -> {
                    _watchListUiState.value =
                        _watchListUiState.value.copy(isInWatchedErrorMessage = response.error)
                    _watchListUiState.value =
                        _watchListUiState.value.copy(isInWatchedIsLoading = false)
                }

                is NetworkResponse.Success -> {
                    _watchListUiState.value =
                        _watchListUiState.value.copy(isInWatchedList = response.data)
                    _watchListUiState.value =
                        _watchListUiState.value.copy(isInWatchedIsLoading = false)
                }
            }
        }
    }

}