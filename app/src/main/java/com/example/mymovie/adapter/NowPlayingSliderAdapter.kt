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
        viewHolder.binding.data = sliderItem

        Glide.with(viewHolder.itemView.rootView)
            .addDefaultRequestListener(object : RequestListener<Any> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Any>?,
                    isFirstResource: Boolean
                ): Boolean {
                    viewHolder.binding.ivGifContainer.visibility = View.GONE
                    return false
                }

                override fun onResourceReady(
                    resource: Any?,
                    model: Any?,
                    target: Target<Any>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    viewHolder.binding.ivGifContainer.visibility = View.GONE
                    return false
                }

            })
            .load(getPathUrl(sliderItem))
            .fitCenter()
            .into(viewHolder.binding.ivAutoImageSlider)
    }

    private fun getPathUrl(sliderItem: NowPlayingModel.Result): String {
        return "${RetrofitHelper.PICTURE_BASE_URL}${sliderItem.poster_path}"
    }
}