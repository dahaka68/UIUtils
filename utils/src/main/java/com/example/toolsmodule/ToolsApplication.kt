package com.example.toolsmodule

import android.app.Application
import android.content.Context

class ToolsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    override fun getApplicationContext(): Context {
        return super.getApplicationContext()
    }

    companion object {
        lateinit var instance: ToolsApplication
            private set
    }
}