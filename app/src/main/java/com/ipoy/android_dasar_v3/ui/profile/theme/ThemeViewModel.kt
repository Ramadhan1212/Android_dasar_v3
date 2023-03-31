package com.ipoy.android_dasar_v3.ui.profile.theme

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ThemeViewModel(private val pref: ThemePref): ViewModel() {
    fun get():LiveData<Boolean> {
        return pref.get().asLiveData()
    }

    fun save (isDarkModeActive: Boolean){
        viewModelScope.launch {
            pref.save(isDarkModeActive)
        }
    }
}