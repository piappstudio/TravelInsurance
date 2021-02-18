package com.piappstudio.travelinsurance.common
import android.app.Application

class TIApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }
    companion object {
        var INSTANCE:TIApplication? = null
    }
}