package com.p97.toolsmodule

class StateData<T> {
    var status: DataStatus
        private set
    var data: T?
        private set
    var error: String?
        private set

    fun loading(): StateData<T> {
        status = DataStatus.LOADING
        data = null
        error = null
        return this
    }

    fun success(data: T): StateData<T> {
        status = DataStatus.SUCCESS
        this.data = data
        error = null
        return this
    }

    fun error(error: Throwable): StateData<T> {
        status = DataStatus.ERROR
        data = null
        this.error = error.message
        return this
    }

    fun error(error: String): StateData<T> {
        status = DataStatus.ERROR
        data = null
        this.error = error
        return this
    }

    enum class DataStatus {
        CREATED, SUCCESS, ERROR, LOADING
    }

    init {
        status = DataStatus.CREATED
        data = null
        error = null
    }
}