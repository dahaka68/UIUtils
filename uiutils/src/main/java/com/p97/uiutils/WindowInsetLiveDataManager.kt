package com.p97.uiutils

import androidx.lifecycle.LiveData

class WindowInsetLiveDataManager {

    private lateinit var _insetData: StateMutableLiveData<InsetData>
    private val inset: StateMutableLiveData<InsetData>
        get() {
            if (!::_insetData.isInitialized) {
                _insetData = StateMutableLiveData()
            }
            return _insetData
        }

    val insetLiveData: LiveData<StateData<InsetData>>
        get() = inset as LiveData<StateData<InsetData>>

    private fun postInsetData(newInset: InsetData) {
        if (newInset.top == 0 && newInset.bottom == 0 && newInset.left == 0 && newInset.right == 0) return

        val prevInset = inset.value as? InsetData
        if (prevInset?.top == newInset.top && prevInset.bottom == newInset.bottom && prevInset.left == newInset.left && prevInset.right == newInset.right) return

        inset.postSuccess(newInset)
    }

    fun postInsets(left: Int, top: Int, right: Int, bottom: Int) {
        postInsetData(InsetData(left, top, right, bottom))
    }

    inner class InsetData(val left: Int, val top: Int, val right: Int, val bottom: Int) {

        override fun toString(): String {
            return "InsetData{" +
                    "left=" + left +
                    ", top=" + top +
                    ", right=" + right +
                    ", bottom=" + bottom +
                    '}'
        }
    }

    private object HOLDER {
        val INSTANCE = WindowInsetLiveDataManager()
    }

    companion object {
        val instance: WindowInsetLiveDataManager by lazy { HOLDER.INSTANCE }
    }
}