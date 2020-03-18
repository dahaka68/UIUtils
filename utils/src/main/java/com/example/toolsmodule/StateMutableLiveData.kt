package com.example.toolsmodule

import androidx.lifecycle.MutableLiveData

class StateMutableLiveData<T> : MutableLiveData<StateData<T>?>() {
    /**
     * Use this to put the Data on a LOADING Status
     */
    fun postLoading() {
        postValue(StateData<T>().loading())
    }

    /**
     * Use this to put the Data on a ERROR DataStatus
     * @param throwable the error to be handled
     */
    fun postError(throwable: Throwable?) {
        postValue(StateData<T>().error(throwable!!))
    }

    fun postError(error: String?) {
        postValue(StateData<T>().error(error!!))
    }

    /**
     * Use this to put the Data on a SUCCESS DataStatus
     * @param data
     */
    fun postSuccess(data: T) {
        postValue(StateData<T>().success(data))
    }
}