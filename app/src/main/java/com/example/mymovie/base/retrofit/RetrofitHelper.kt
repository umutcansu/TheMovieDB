package com.example.mymovie.base.retrofit

import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object RetrofitHelper
{
    private var retrofit : Retrofit? = null

    private var  service : RetrofitService? = null

    private var okHttpClient = OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.MINUTES)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .addInterceptor { chain ->
            val original: Request = chain.request()
            val originalHttpUrl: HttpUrl = original.url()

            val url = originalHttpUrl.newBuilder()
                .addQueryParameter(API_QUERY_KEY_NAME, API_KEY_QUERY_KEY_VALUE)
                .addQueryParameter(LANGUAGE_QUERY_KEY_NAME, LANGUAGE_QUERY_KEY_VALUE)
                .build()

            val requestBuilder: Request.Builder = original.newBuilder()
                .url(url)

            val request: Request = requestBuilder.build()
            chain.proceed(request)
        }
        .build()

    private const val  BASE_URL = "https://api.themoviedb.org/3/"
    private const val  API_QUERY_KEY_NAME = "api_key"
    private const val  API_KEY_QUERY_KEY_VALUE = "c6649d226aff015282c8c4bb5ff4ede5"
    private const val  LANGUAGE_QUERY_KEY_NAME = "language"
    private const val  LANGUAGE_QUERY_KEY_VALUE = "en-US"

    private val Client : Retrofit?
        get()
        {
            if(retrofit == null)
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create()).build()

            return retrofit
        }


    val Call : RetrofitService
        get()
        {
            if(service == null)
                service =  Client!!.create(RetrofitService::class.java)

            return service!!
        }
}