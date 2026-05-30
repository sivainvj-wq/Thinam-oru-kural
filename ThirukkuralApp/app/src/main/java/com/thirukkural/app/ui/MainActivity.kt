package com.thirukkural.app.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.thirukkural.app.R
import com.thirukkural.app.data.repository.KuralRepository
import com.thirukkural.app.databinding.ActivityMainBinding
import com.thirukkural.app.notifications.NotificationHelper
import com.thirukkural.app.utils.NotificationScheduler
import com.thirukkural.app.utils.PreferencesManager
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var prefs: PreferencesManager

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted && prefs.isNotificationEnabled()) {
            NotificationScheduler.scheduleDaily(this)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set toolbar as action bar
        setSupportActionBar(binding.toolbar)

        prefs = PreferencesManager(this)

        // Initialize DB from assets in background
        val repo = KuralRepository(this)
        lifecycleScope.launch {
            repo.initializeDatabase()
        }

        // Setup Navigation
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        val appBarConfiguration = AppBarConfiguration(
            setOf(R.id.navigation_preface, R.id.navigation_today, R.id.navigation_browse, R.id.navigation_settings)
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.bottomNavigation.setupWithNavController(navController)

        // Request notification permission (Android 13+)
        requestNotificationPermission()

        // Schedule daily notification if enabled
        if (prefs.isNotificationEnabled()) {
            NotificationScheduler.scheduleDaily(this)
        }

        // Handle deep link from notification tap
        handleNotificationIntent()
    }

    override fun onNewIntent(intent: android.content.Intent?) {
        super.onNewIntent(intent)
        setIntent(intent)
        handleNotificationIntent()
    }

    private fun handleNotificationIntent() {
        val kuralNumber = intent?.getIntExtra(
            NotificationHelper.EXTRA_KURAL_NUMBER, -1
        ) ?: -1
        if (kuralNumber > 0) {
            val bundle = Bundle().apply { putInt("kuralNumber", kuralNumber) }
            // Navigate to today tab with the specific kural number
            navController.navigate(R.id.navigation_today, bundle)
        }
    }

    private fun requestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    this, Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
