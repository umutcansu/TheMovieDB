package com.example.mymovie.ui


import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.mymovie.base.core.BaseFragment
import com.example.mymovie.base.retrofit.RetrofitHelper
import com.example.mymovie.databinding.FragmentDetailBinding
import com.example.mymovie.model.MovieDetailModel
import com.example.mymovie.model.UpComingModel
import com.example.mymovie.vm.DetailFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import android.content.Intent
import android.net.Uri
import com.example.mymovie.util.GlideUtil


@AndroidEntryPoint
class DetailFragment :
    BaseFragment<FragmentDetailBinding, DetailFragmentViewModel>() {

    var upComing: UpComingModel.Result? = null

    override fun getBinding(): FragmentDetailBinding {
        return FragmentDetailBinding.inflate(layoutInflater)
    }

    override fun getViewModel(): DetailFragmentViewModel {
        return ViewModelProvider(this).get(DetailFragmentViewModel::class.java)
    }

    override fun initUI() {
        getArgs()
        initObservable()
    }

    override fun onBackPressedCustom() {
        findNavController().navigate(DetailFragmentDirections.actionDetailFragmentToMainFragment())
    }

    private fun getArgs() {
        val bundle = arguments
        bundle?.let {
            val args = DetailFragmentArgs.fromBundle(bundle)
            upComing = args.upComingDetail
            upComing?.let { up ->
                mViewModel.getDetails(up.id)
            }
        }
    }

    private fun setVariableBinding() {
        mBinding.vm = mViewModel
        mBinding.data = mViewModel.details.value
        getPoster()
    }

    private fun getPoster() {
        mBinding.apply {
            GlideUtil.loadPoster(ivPoster,pbLoading,upComing!!.imagePath)
        }
    }


    private fun initObservable() {
        mViewModel.apply {
            details.observe(viewLifecycleOwner) {
                setVariableBinding()
            }
            clickImdb.observe(viewLifecycleOwner) {
                if (it != null) openImdbLink(it)
            }
        }
    }

    private fun openImdbLink(movie: MovieDetailModel) {
        val url = "https://www.imdb.com/title/${movie.imdb_id}"
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }
}