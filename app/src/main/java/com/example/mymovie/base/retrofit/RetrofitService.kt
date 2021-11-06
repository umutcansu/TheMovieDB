package com.example.mymovie.base.retrofit

import com.example.mymovie.model.NowPlayingModel
import com.example.mymovie.model.UpComingModel
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitService {
    @GET("movie/now_playing")
    fun getNowPlaying(): Call<NowPlayingModel>

    @GET("movie/upcoming")
    fun getUpcoming():Call<UpComingModel>
}