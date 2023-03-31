package com.ipoy.android_dasar_v3.ui.profile.theme

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.switchmaterial.SwitchMaterial
import com.ipoy.android_dasar_v3.R


private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class ThemeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_theme)

        val mode = findViewById<SwitchMaterial>(R.id.sm_darkmode)

        val pref = ThemePref.get(dataStore)
        val sVM = ViewModelProvider(this, ThemeVMFactory(pref)).get(
            ThemeViewModel::class.java
        )

        sVM.get().observe(this) {
            isDarkModeActive: Boolean ->
            if (isDarkModeActive){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                mode.isChecked = true
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                mode.isChecked = false
            }
        }

        mode.setOnCheckedChangeListener { _: CompoundButton?, isChecked: Boolean ->
            sVM.save(isChecked)
        }
    }
}