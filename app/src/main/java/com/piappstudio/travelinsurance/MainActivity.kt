package com.piappstudio.travelinsurance

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.piappstudio.pilibrary.ui.PIBaseActivity
import com.piappstudio.pilibrary.utility.dismissKeyboard
import com.piappstudio.travelinsurance.databinding.ActivityMainBinding

class MainActivity : PIBaseActivity() {

    private var binding:ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        val navHost = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val appConfiguration = AppBarConfiguration(topLevelDestinationIds = setOf(R.id.welcomeFragment, R.id.homeFragment),
            fallbackOnNavigateUpListener = ::onSupportNavigateUp)
        setupActionBarWithNavController(navHost.navController, appConfiguration)
        //setupActionBarWithNavController( navHost.navController)
        navHost.navController.addOnDestinationChangedListener { _, _, _ ->
            this.dismissKeyboard()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true

    }
}