package com.example.mymovie.adapter

import androidx.paging.PagingSource
import com.example.mymovie.base.core.BaseRepository
import com.example.mymovie.model.UpComingModel
import javax.inject.Inject

class UpComingPagingSource @Inject constructor(private val repository: BaseRepository)
    : PagingSource<Int, UpComingModel.Result>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UpComingModel.Result> {
        val position = params.key ?: STARTING_INDEX
        return try {
            val movies = repository.httpClient.getUpcoming(position)

            LoadResult.Page(
                data = movies.results,
                prevKey = if (position == STARTING_INDEX) null else position - 1,
                nextKey = if (movies.results.isEmpty()) null else position + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    companion object {
        const val STARTING_INDEX = 1
    }

}