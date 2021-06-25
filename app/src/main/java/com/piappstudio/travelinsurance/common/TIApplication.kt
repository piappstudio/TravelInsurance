package com.piappstudio.travelinsurance.common
import android.app.Application
import com.piappstudio.travelinsurance.model.mbo.User
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TIApplication:Application() {
    companion object {
        var currUser:User? = null
    }
}