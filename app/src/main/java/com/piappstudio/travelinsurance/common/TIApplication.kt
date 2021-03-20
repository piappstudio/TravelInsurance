package com.piappstudio.travelinsurance.common
import android.app.Application
import com.piappstudio.travelinsurance.model.repository.TravelRepository

class TIApplication:Application() {

    private val database by lazy {TravelDatabase.getDatabase(this)}
    val repository by lazy { TravelRepository(database.userDao()) }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }
    companion object {
        var INSTANCE:TIApplication? = null
    }
}