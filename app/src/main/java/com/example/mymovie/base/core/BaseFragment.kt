package com.example.mymovie.base.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.example.mymovie.BR


abstract class BaseFragment<T : ViewDataBinding, V : BaseViewModel> : Fragment() {
    private var _binding: T? = null
    protected lateinit var mRootView: View
    protected val mBinding: T
        get() = _binding!!
    protected lateinit var mViewModel: V

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mViewModel = getViewModel()
        _binding = getBinding()
        mViewModel.init()
        mRootView = mBinding.root
        return mRootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.setVariable(BR.vm, mViewModel)
        mBinding.lifecycleOwner = this
        mBinding.executePendingBindings()
        initUI()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    open fun initUI(){
        //Empty body}
    }

    abstract fun getBinding(): T

    abstract fun getViewModel(): V
}