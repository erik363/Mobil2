package com.example.android.navigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PlayerOneWonModelFactory(private val finalScore: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PlayerOneWonModel::class.java)) {
            return PlayerOneWonModel(finalScore) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}