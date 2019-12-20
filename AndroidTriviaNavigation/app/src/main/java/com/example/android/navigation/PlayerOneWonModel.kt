package com.example.android.navigation

import android.util.Log
import androidx.lifecycle.ViewModel

class PlayerOneWonModel(finalscore: Int) : ViewModel() {
    var score = finalscore
    init {
        Log.i("ScoreViewModel", "Final score is $finalscore")
    }
}