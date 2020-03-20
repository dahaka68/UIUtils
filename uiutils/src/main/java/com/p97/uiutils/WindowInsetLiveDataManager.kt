package com.p97.uiutils

import androidx.lifecycle.LiveData

class WindowInsetLiveDataManager {
    //LEFT
    private lateinit var _insetdata: StateMutableLiveData<InsetData>
    private val inset: StateMutableLiveData<InsetData>
        get() {
            if (!::_insetdata.isInitialized) {
                _insetdata = StateMutableLiveData()
            }
            return _insetdata
        }

    val insetLiveData: LiveData<StateData<InsetData>>
        get() = inset as LiveData<StateData<InsetData>>

    protected fun postInsetData(left: InsetData) {
        inset.postSuccess(left)
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