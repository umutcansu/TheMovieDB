package com.example.mymovie.base.core

import com.example.mymovie.base.retrofit.RetrofitService

class BaseRepository  constructor(
     val httpClient: RetrofitService
)