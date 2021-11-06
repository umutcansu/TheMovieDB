package com.example.mymovie.ui


import androidx.lifecycle.ViewModelProvider
import com.example.mymovie.base.core.BaseFragment
import com.example.mymovie.databinding.FragmentDetailBinding
import com.example.mymovie.vm.DetailFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment :
    BaseFragment<FragmentDetailBinding, DetailFragmentViewModel>() {


    override fun getBinding(): FragmentDetailBinding {
        return FragmentDetailBinding.inflate(layoutInflater)
    }

    override fun getViewModel(): DetailFragmentViewModel {
        return ViewModelProvider(this).get(DetailFragmentViewModel::class.java)
    }

    override fun initUI() {
        initObservable()
    }


    private fun initObservable() {
    }
}