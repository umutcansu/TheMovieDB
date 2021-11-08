package com.example.mymovie.ui


import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.example.mymovie.adapter.NowPlayingSliderAdapter
import com.example.mymovie.adapter.UpComingAdapter
import com.example.mymovie.base.core.BaseFragment
import com.example.mymovie.databinding.FragmentMainBinding
import com.example.mymovie.model.NowPlayingModel
import com.example.mymovie.model.UpComingModel
import com.example.mymovie.vm.MainFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment :
    BaseFragment<FragmentMainBinding, MainFragmentViewModel>() {

    private lateinit var adapter: UpComingAdapter

    override fun getBinding(): FragmentMainBinding {
        return FragmentMainBinding.inflate(layoutInflater)
    }

    override fun getViewModel(): MainFragmentViewModel {
        return ViewModelProvider(this).get(MainFragmentViewModel::class.java)
    }

    override fun initUI() {
        isLoadingSlider(false)
        initUpComingAdapter()
        initObservable()
    }


    private fun initObservable() {
        mViewModel.apply {
            upComingClick.observe(viewLifecycleOwner) {
                it?.let {
                    openDetails(it)
                }
            }
            nowPlaying.observe(viewLifecycleOwner) {
                if (it != null)
                    initSlider(it)
            }
        }

        lifecycleScope.launch {
            mViewModel.getUpComing().collectLatest {
                adapter.submitData(it)
            }
        }
    }

    private fun isLoadingUpComing(state: Boolean) {
        if (state) {
            mBinding.rvUpcoming.visibility = View.VISIBLE
            mBinding.pbUpComing.visibility = View.GONE
        } else {
            mBinding.rvUpcoming.visibility = View.GONE
            mBinding.pbUpComing.visibility = View.VISIBLE
        }
    }

    private fun isLoadingSlider(state: Boolean) {
        if (state) {
            mBinding.imageSlider.visibility = View.VISIBLE
            mBinding.pbSlider.visibility = View.GONE
        } else {
            mBinding.imageSlider.visibility = View.GONE
            mBinding.pbSlider.visibility = View.VISIBLE
        }
    }

    private fun initSlider(result: NowPlayingModel) {
        isLoadingSlider(true)
        mBinding.imageSlider.setSliderAdapter(NowPlayingSliderAdapter(result.results.filter { it.poster_path.isNotEmpty() }
            .toList()))
    }

    private fun initUpComingAdapter() {
        adapter = UpComingAdapter(mViewModel)
        adapter.addLoadStateListener { combinedLoadStates ->
            when (val loadState = combinedLoadStates.source.refresh) {
                is LoadState.NotLoading -> {
                    isLoadingUpComing(true)
                }
                is LoadState.Loading -> {
                    isLoadingUpComing(false)
                }
                is LoadState.Error -> {
                    Log.e(::MainFragment.javaClass.name, loadState.error.localizedMessage ?: "")
                }
            }
        }
        mBinding.rvUpcoming.adapter = adapter
    }

    private fun openDetails(model: UpComingModel.Result) {
        val direction = MainFragmentDirections.actionMainFragmentToDetailFragment(model)
        findNavController().navigate(direction)
    }

}