package com.example.mymovie.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.mymovie.adapter.UpComingPagingSource
import com.example.mymovie.base.core.BaseRepository
import com.example.mymovie.base.core.BaseViewModel
import com.example.mymovie.model.NowPlayingModel
import com.example.mymovie.model.UpComingModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModel @Inject constructor(private val repository: BaseRepository) :
    BaseViewModel(repository) {

    val nowPlaying: MutableLiveData<NowPlayingModel> = MutableLiveData()
    val upComingClick: MutableLiveData<UpComingModel.Result> = MutableLiveData()

    private val PAGE_SIZE = 20


    override fun init() {
        getNowPlaying()
        getUpComing()
    }

    private fun getNowPlaying() {
        httpClient.getNowPlaying()
            .enqueue(object : retrofit2.Callback<NowPlayingModel> {
                override fun onResponse(
                    call: Call<NowPlayingModel>,
                    response: Response<NowPlayingModel>
                ) {
                    nowPlaying.value = response.body()
                }

                override fun onFailure(call: Call<NowPlayingModel>, t: Throwable) {

                }

            })
    }


    fun getUpComing(): Flow<PagingData<UpComingModel.Result>> {
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = { UpComingPagingSource(repository) }
        ).flow
            .cachedIn(viewModelScope)
    }

    fun clickItem(model:UpComingModel.Result){
        upComingClick.value = model
    }

}