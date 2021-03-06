package com.example.mymovie.base.retrofit

import com.example.mymovie.model.MovieDetailModel
import com.example.mymovie.model.NowPlayingModel
import com.example.mymovie.model.UpComingModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService {
    @GET("movie/now_playing")
    fun getNowPlaying(): Call<NowPlayingModel>

    @GET("movie/upcoming")
    suspend fun getUpcoming(@Query("page")page:Int):UpComingModel

    @GET("movie/{movie_id}")
    fun getDetails(@Path("movie_id")id:Long): Call<MovieDetailModel>
}