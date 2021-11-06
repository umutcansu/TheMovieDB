package com.example.mymovie.vm

import androidx.lifecycle.MutableLiveData
import com.example.mymovie.base.core.BaseRepository
import com.example.mymovie.base.core.BaseViewModel
import com.example.mymovie.model.NowPlayingModel
import com.example.mymovie.model.UpComingModel
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModel @Inject constructor(repository: BaseRepository) :
    BaseViewModel(repository) {

    val nowPlaying: MutableLiveData<NowPlayingModel> = MutableLiveData()
    val upComing: MutableLiveData<UpComingModel> = MutableLiveData()

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

    private fun getUpComing() {
        httpClient.getUpcoming()
            .enqueue(object : retrofit2.Callback<UpComingModel> {
                override fun onResponse(
                    call: Call<UpComingModel>,
                    response: Response<UpComingModel>
                ) {
                    upComing.value = response.body()
                }

                override fun onFailure(call: Call<UpComingModel>, t: Throwable) {

                }

            })
    }
}