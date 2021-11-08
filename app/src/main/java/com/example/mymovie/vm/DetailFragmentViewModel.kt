package com.example.mymovie.vm

import androidx.lifecycle.MutableLiveData
import com.example.mymovie.base.core.BaseRepository
import com.example.mymovie.base.core.BaseViewModel
import com.example.mymovie.model.MovieDetailModel
import com.example.mymovie.model.NowPlayingModel
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class DetailFragmentViewModel @Inject constructor(repository: BaseRepository) :
    BaseViewModel(repository) {

    val details:MutableLiveData<MovieDetailModel> = MutableLiveData()
    val clickImdb:MutableLiveData<MovieDetailModel> = MutableLiveData()

    override fun init() {

    }

    fun getDetails(id:Long) {
        httpClient.getDetails(id)
            .enqueue(object : retrofit2.Callback<MovieDetailModel> {
                override fun onResponse(
                    call: Call<MovieDetailModel>,
                    response: Response<MovieDetailModel>
                ) {
                    if(response.body() != null)
                        details.value = response.body()
                }

                override fun onFailure(call: Call<MovieDetailModel>, t: Throwable) {

                }

            })
    }

    fun imdbClick(movie:MovieDetailModel){
        clickImdb.value = movie
    }
}