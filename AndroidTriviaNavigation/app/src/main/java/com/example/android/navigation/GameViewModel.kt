package com.example.android.navigation

import android.app.Application
import android.os.CountDownTimer
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.*
import com.example.android.navigation.database.EredmenyDAO
import com.example.android.navigation.database.EredmenyEntity
import kotlinx.coroutines.*

class GameViewModel(val database: EredmenyDAO,
                    application: Application) : AndroidViewModel(application) {

    val boardC = Array(3){ arrayOfNulls<ImageView>(3)}
    var next = 0
    var board = Board()
    var nev1 : String = "unknown1"
    var nev2 : String = "unknown1"

    //LiveData
    private val _score = MutableLiveData<Int>()
    val score:LiveData<Int>
        get() = _score
    val scoreString = Transformations.map(score){
        score -> score.toString()
    }

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private var eredmenyenty = MutableLiveData<EredmenyEntity?>()

//LiveData transformation
    companion object {

        // Time when the game is over
        private const val DONE = 0L

        // Countdown time interval
        private const val ONE_SECOND = 1000L

        // Total time for the game
        private const val COUNTDOWN_TIME = 60000L
    }

    private val _currentTime = MutableLiveData<Long>()
    val currentTime: LiveData<Long>
        get() = _currentTime

    private val timer: CountDownTimer
//////
    init {
        Log.i("GameViewModel", "GameViewModel created!")
        _score.value = 9

        timer = object : CountDownTimer(COUNTDOWN_TIME, ONE_SECOND) {

        override fun onTick(millisUntilFinished: Long) {
            _currentTime.value = millisUntilFinished/ONE_SECOND
        }

        override fun onFinish() {
            _currentTime.value = DONE
           // onGameFinish()
        }
    }

    timer.start()

    initializeEredmenyenty()
    }

    private fun initializeEredmenyenty(){
        uiScope.launch {
            eredmenyenty.value = getEredmenyFromDatabase()
        }
    }

    private suspend fun getEredmenyFromDatabase(): EredmenyEntity? {
        return withContext(Dispatchers.IO) {
            var eredmeny = database.getEredmeny()
            eredmeny
        }
    }

    fun updateScoreText() {
        _score.value = (score.value)?.minus(1)
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun onStartTracking(){
        uiScope.launch {
            val ujEredmeny= EredmenyEntity()
            var i = _score.value
            ujEredmeny.name1 = nev1
            ujEredmeny.name2 = nev2
            ujEredmeny.pont = i.hashCode()
                insert(ujEredmeny)
            Toast.makeText(getApplication(), ujEredmeny.name1, Toast.LENGTH_SHORT).show()
                eredmenyenty.value = getEredmenyFromDatabase()
            }
        }

    private suspend fun insert(eredmeny: EredmenyEntity) {
        withContext(Dispatchers.IO) {
            database.insert(eredmeny)
        }
    }



}