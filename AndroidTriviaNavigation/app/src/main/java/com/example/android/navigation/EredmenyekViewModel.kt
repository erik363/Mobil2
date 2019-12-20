package com.example.android.navigation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.android.navigation.database.EredmenyDAO
import com.example.android.navigation.database.EredmenyEntity
import kotlinx.coroutines.*

class EredmenyekViewModel(
        val database: EredmenyDAO,
        application: Application) : AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val matches = database.getAllEredmeny()

    val eredmenyekString = Transformations.map(matches) { eredmenyek ->
        formatEredmenyek(eredmenyek, application.resources)
    }

    private var actual = MutableLiveData<EredmenyEntity?>()

    init {
        initializeEredmenyek()
    }

    private fun initializeEredmenyek() {
        uiScope.launch {
            actual.value = getEredmenyekFromDatabase()
        }
    }

    private suspend fun getEredmenyekFromDatabase(): EredmenyEntity? {
        return withContext(Dispatchers.IO) {
            var match = database.getLast()
            match
        }
    }

    fun onStartTracking() {
        uiScope.launch {
            val ujEredmeny = EredmenyEntity()
            insert(ujEredmeny)
            actual.value = getEredmenyekFromDatabase()
        }
    }

    private suspend fun insert(eredmeny: EredmenyEntity) {
        withContext(Dispatchers.IO) {
            database.insert(eredmeny)
        }
    }
/*
    fun onStopTracking() {
        uiScope.launch {
            val oldNight = actual.value ?: return@launch
            oldNight.name1 = "gizi"
            update(oldNight)
        }
    }

    private suspend fun update(night: EredmenyEntity) {
        withContext(Dispatchers.IO) {
            database.update(night)
        }
    }
*/
    fun onClear() {
        uiScope.launch {
            clear()
            actual.value = null
        }
    }

    suspend fun clear() {
        withContext(Dispatchers.IO) {
            database.clear()
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}

