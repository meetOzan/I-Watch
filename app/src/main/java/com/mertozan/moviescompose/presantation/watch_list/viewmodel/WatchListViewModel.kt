package com.mertozan.moviescompose.presantation.watch_list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mertozan.moviescompose.data.remote.response.NetworkResponse
import com.mertozan.moviescompose.domain.usecase.GetAllInWatchListContents
import com.mertozan.moviescompose.domain.usecase.GetAllWatchedListContents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WatchListViewModel @Inject constructor(
    private val getAllWatchedListContents: GetAllWatchedListContents,
    private val getAllInWatchListContents: GetAllInWatchListContents
) : ViewModel() {

    private val _watchListUiState = MutableStateFlow(WatchListUiState())
    val watchListUiState = _watchListUiState.asStateFlow()

    init {
        getWatchedContentList()
        getIsInWatchedContentList()
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