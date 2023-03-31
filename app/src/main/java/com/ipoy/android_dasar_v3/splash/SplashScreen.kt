package com.ipoy.android_dasar_v3.splash

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.ipoy.android_dasar_v3.MainActivity
import com.ipoy.android_dasar_v3.R
import com.ipoy.android_dasar_v3.ui.profile.theme.ThemePref
import com.ipoy.android_dasar_v3.ui.profile.theme.ThemeVMFactory
import com.ipoy.android_dasar_v3.ui.profile.theme.ThemeViewModel

class SplashScreen : AppCompatActivity() {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val pref = ThemePref.get(dataStore)
        val sVM = ViewModelProvider(this, ThemeVMFactory(pref)).get(
            ThemeViewModel::class.java
        )
        sVM.get().observe(this
        ) { isDarkModeActive: Boolean ->
            if (isDarkModeActive) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

        val delay = getString(R.string.delay).toLong()

        supportActionBar?.hide()

        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, delay)
    }
}