package com.example.mymovie.base.di

import com.example.mymovie.base.core.BaseRepository
import com.example.mymovie.base.retrofit.RetrofitHelper
import com.example.mymovie.base.retrofit.RetrofitService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object AppModule {

    @ViewModelScoped
    @Provides
    fun injectRetrofit(): RetrofitService {
        return  RetrofitHelper.Call
    }

    @ViewModelScoped
    @Provides
    fun injectRepository(httpClient: RetrofitService): BaseRepository {
        return BaseRepository(httpClient)
    }
}