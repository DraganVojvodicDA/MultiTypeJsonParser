package com.vojvodic.sample

import android.app.Application
import com.vojvodic.sample.common.dependencyinjection.AppContainer

class SampleApp : Application() {


    companion object {
        private lateinit var instance: SampleApp
        private var appContainer: AppContainer? = null

        fun getAppInstance(): SampleApp {
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    fun  getAppContainer(): AppContainer {

        if (appContainer == null) {
            appContainer = AppContainer()
        }
        return appContainer!!
    }
}
