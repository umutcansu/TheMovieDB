package com.example.mymovie.ui


import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.mymovie.adapter.NowPlayingSliderAdapter
import com.example.mymovie.base.core.BaseFragment
import com.example.mymovie.databinding.FragmentMainBinding
import com.example.mymovie.model.NowPlayingModel
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
        mViewModel.apply {
            upComing.observe(viewLifecycleOwner) {
                Log.e("","")
            }
            nowPlaying.observe(viewLifecycleOwner) {
                if(it != null)
                    initSlider(it)
            }
        }
    }

    private fun initSlider(result: NowPlayingModel){
        mBinding.imageSlider.setSliderAdapter(NowPlayingSliderAdapter(result.results.filter { it.poster_path.isNotEmpty()}.toList()))
    }
}