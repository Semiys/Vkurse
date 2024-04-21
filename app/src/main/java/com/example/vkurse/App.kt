package com.example.vkurse
import android.app.Application
import com.yandex.mapkit.MapKitFactory

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        MapKitFactory.setApiKey("b24e54ca-8c0c-4986-a17a-091f18cbe011")
        MapKitFactory.initialize(this)
    }

    override fun onTerminate() {
        MapKitFactory.getInstance().onStop()
        super.onTerminate()
    }
}