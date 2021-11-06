package com.example.mymovie.vm

import com.example.mymovie.base.core.BaseRepository
import com.example.mymovie.base.core.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModel @Inject constructor(repository: BaseRepository) :
    BaseViewModel(repository) {

    override fun init() {

    }
}