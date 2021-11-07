package com.example.mymovie.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.mymovie.R
import com.example.mymovie.base.retrofit.RetrofitHelper
import com.example.mymovie.databinding.UpcommingLayoutItemBinding
import com.example.mymovie.model.UpComingModel
import com.example.mymovie.vm.MainFragmentViewModel

class UpComingAdapter(val viewModel: MainFragmentViewModel): PagingDataAdapter<UpComingModel.Result, UpComingAdapter.MovieViewHolder>(MovieDiffCallback) {

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)!!
        holder.binding.data = movie
        holder.binding.vm = viewModel
        Glide.with(holder.itemView.rootView)
            .addDefaultRequestListener(object : RequestListener<Any> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Any>?,
                    isFirstResource: Boolean
                ): Boolean {
                    holder.binding.pbLoading.visibility = View.GONE
                    return false
                }

                override fun onResourceReady(
                    resource: Any?,
                    model: Any?,
                    target: Target<Any>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    holder.binding.pbLoading.visibility = View.GONE
                    return false
                }

            })
            .load(getPathUrl(movie))
            .fitCenter()
            .into(holder.binding.ivPoster)
    }

    private fun getPathUrl(sliderItem: UpComingModel.Result): String {
        return "${RetrofitHelper.PICTURE_BASE_URL}${sliderItem.poster_path}"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = DataBindingUtil.inflate<UpcommingLayoutItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.upcomming_layout_item,
            parent,
            false
        )
        return MovieViewHolder(view)
    }

    class MovieViewHolder(val binding: UpcommingLayoutItemBinding) : RecyclerView.ViewHolder(binding.root)

    object MovieDiffCallback: DiffUtil.ItemCallback<UpComingModel.Result>() {
        override fun areItemsTheSame(oldItem: UpComingModel.Result, newItem: UpComingModel.Result): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UpComingModel.Result, newItem: UpComingModel.Result): Boolean {
            return oldItem == newItem
        }
    }
}