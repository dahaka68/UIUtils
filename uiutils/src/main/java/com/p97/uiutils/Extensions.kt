package com.p97.uiutils

import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

private var viewDataBinding: ViewDataBinding? = null

fun initInsets(view: View, lifecycleOwner: LifecycleOwner, insets: Int) {
    ViewCompat.setOnApplyWindowInsetsListener(view) { _: View?, insetsCompat: WindowInsetsCompat ->
        WindowInsetLiveDataManager.instance.postInsets(
                insetsCompat.systemWindowInsetLeft,
                insetsCompat.systemWindowInsetTop,
                insetsCompat.systemWindowInsetRight,
                insetsCompat.systemWindowInsetBottom)
        insetsCompat.consumeSystemWindowInsets()
    }
    viewDataBinding = DataBindingUtil.findBinding(view)
    ViewCompat.requestApplyInsets(view)
    WindowInsetLiveDataManager.instance.insetLiveData.observe(lifecycleOwner,
            Observer { insetDataStateData ->
                onInsetDataChanged(
                    insetDataStateData.data,
                    insets
                )
            })
}

/**
 * Move inset into binding to set paddings for insets
 * @param insetData
 */
private fun onInsetDataChanged(insetData: WindowInsetLiveDataManager.InsetData?, insets: Int) {
    viewDataBinding?.setVariable(insets, insetData)
}