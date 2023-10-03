package com.mertozan.moviescompose.domain.usecase

import com.mertozan.moviescompose.data.mapper.topSeriesEntityToDetailItem
import com.mertozan.moviescompose.data.remote.response.NetworkResponse
import com.mertozan.moviescompose.domain.model.ContentModel
import com.mertozan.moviescompose.domain.repository.ContentRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetSingleTopSeries @Inject constructor(
    private val contentRepository: ContentRepository
) {
    operator fun invoke(seriesId: Int): NetworkResponse<ContentModel> {
        return try {
            NetworkResponse.Success(
                contentRepository.getSingleTopSeries(seriesId = seriesId)
                    .topSeriesEntityToDetailItem()
            )
        } catch (e: HttpException) {
            NetworkResponse.Error(e)
        } catch (e: IOException) {
            NetworkResponse.Error(e)
        }
    }
}