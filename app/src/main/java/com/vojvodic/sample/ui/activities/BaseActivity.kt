package com.vojvodic.sample.ui.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.vojvodic.sample.R
import com.vojvodic.sample.SampleApp
import com.vojvodic.sample.common.dependencyinjection.AppContainer

abstract class BaseActivity : AppCompatActivity() {

    private lateinit var appContainer: AppContainer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appContainer = SampleApp.getAppInstance().getAppContainer()

        setContentView(provideLayout())
    }

    override fun setContentView(layoutResID: Int) {
        super.setContentView(R.layout.base_activity)

        val childActivityContainer: FrameLayout = findViewById(R.id.childActivityContainer)
        val childActivity =
            LayoutInflater.from(this).inflate(layoutResID, childActivityContainer, false)
        childActivityContainer.addView(childActivity)

        layoutReady()
    }

    @LayoutRes
    abstract fun provideLayout(): Int

    abstract fun layoutReady()

    protected fun getAppContainer(): AppContainer {
        return appContainer
    }
}