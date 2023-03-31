package com.ipoy.android_dasar_v3.ui.profile.theme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ThemeVMFactory(private val pref: ThemePref): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ThemeViewModel::class.java)) {
            return ThemeViewModel(pref) as T
        }
        throw IllegalArgumentException("Unknown VM Class: " + modelClass.name)
    }
}