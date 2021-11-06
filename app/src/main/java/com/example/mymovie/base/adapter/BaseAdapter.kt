package com.example.mymovie.base.adapter

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovie.base.core.BaseViewModel

abstract class BaseAdapter<D, VH : BaseViewHolder<ViewDataBinding>, VM : BaseViewModel> : RecyclerView.Adapter<BaseViewHolder<ViewDataBinding>>() {

    var dataSource = listOf<D>()
    override fun onBindViewHolder(holder: BaseViewHolder<ViewDataBinding>, position: Int) {
        bind(holder.binding, position, dataSource[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<ViewDataBinding> {
        return getViewHolder(parent, viewType)
    }

    open fun getViewHolder(parent: ViewGroup, viewType: Int) = BaseViewHolder(createBinding(parent, viewType))
    abstract fun createBinding(parent: ViewGroup, viewType: Int): ViewDataBinding
    protected abstract fun bind(binding: ViewDataBinding, position: Int, data: D)

    fun getItem(position: Int) = dataSource[position]
    override fun getItemCount() = dataSource.size
}