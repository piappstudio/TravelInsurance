package com.piappstudio.travelinsurance

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.piappstudio.travelinsurance.common.TIBaseActivity

class MainActivity : TIBaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHost = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        setupActionBarWithNavController( navHost.navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true

    }
}