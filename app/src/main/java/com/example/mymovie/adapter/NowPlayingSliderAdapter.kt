package com.example.mymovie.adapter

import android.view.ViewGroup
import com.example.mymovie.R
import com.smarteist.autoimageslider.SliderViewAdapter
import com.example.mymovie.model.NowPlayingModel
import android.view.LayoutInflater
import android.view.View
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.mymovie.base.retrofit.RetrofitHelper
import com.example.mymovie.databinding.ImageSliderLayoutItemBinding
import com.example.mymovie.util.GlideUtil

class NowPlayingSliderAdapter(
    private val mSliderItems: List<NowPlayingModel.Result> = ArrayList()
) : SliderViewAdapter<NowPlayingSliderAdapter.SliderAdapterVH>() {

    class SliderAdapterVH(val binding: ImageSliderLayoutItemBinding) : ViewHolder(binding.root)

    override fun getCount(): Int {
        return mSliderItems.size
    }

    override fun onCreateViewHolder(parent: ViewGroup): SliderAdapterVH {
        val view = DataBindingUtil.inflate<ImageSliderLayoutItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.image_slider_layout_item,
            parent,
            false
        )
        return SliderAdapterVH(view)
    }

    override fun onBindViewHolder(viewHolder: SliderAdapterVH, position: Int) {
        val sliderItem: NowPlayingModel.Result = mSliderItems[position]
        viewHolder.binding.apply {
            data = sliderItem
            GlideUtil.loadPoster(ivAutoImageSlider,ivGifContainer,sliderItem.imagePath)
        }
    }
}