package com.example.mymovie.util

import android.widget.ImageView
import android.widget.ProgressBar
import com.bumptech.glide.Glide

object GlideUtil {
    fun loadPoster(imageView: ImageView,progressBar: ProgressBar,posterUrl:String){
        Glide.with(imageView)
            .addDefaultRequestListener(object :
                com.bumptech.glide.request.RequestListener<kotlin.Any> {
                override fun onLoadFailed(
                    e: com.bumptech.glide.load.engine.GlideException?,
                    model: kotlin.Any?,
                    target: com.bumptech.glide.request.target.Target<kotlin.Any>?,
                    isFirstResource: kotlin.Boolean
                ): kotlin.Boolean {
                    progressBar.visibility = android.view.View.GONE
                    return false
                }

                override fun onResourceReady(
                    resource: kotlin.Any?,
                    model: kotlin.Any?,
                    target: com.bumptech.glide.request.target.Target<kotlin.Any>?,
                    dataSource: com.bumptech.glide.load.DataSource?,
                    isFirstResource: kotlin.Boolean
                ): kotlin.Boolean {
                    progressBar.visibility = android.view.View.GONE
                    return false
                }

            })
            .load(posterUrl)
            .fitCenter()
            .into(imageView)
    }
}