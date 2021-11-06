package com.example.mymovie.ui


import androidx.lifecycle.ViewModelProvider
import com.example.mymovie.base.core.BaseFragment
import com.example.mymovie.databinding.FragmentMainBinding
import com.example.mymovie.vm.MainFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment :
    BaseFragment<FragmentMainBinding, MainFragmentViewModel>() {


    override fun getBinding(): FragmentMainBinding {
        return FragmentMainBinding.inflate(layoutInflater)
    }

    override fun getViewModel(): MainFragmentViewModel {
        return ViewModelProvider(this).get(MainFragmentViewModel::class.java)
    }

    override fun initUI() {
        initObservable()
    }


    private fun initObservable() {
    }
}