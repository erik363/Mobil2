package com.example.android.navigation.web

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.android.navigation.database.EredmenyDAO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class WebViewModel (val database: EredmenyDAO, application: Application) : AndroidViewModel(application){


    init{
        getPostsProperties()
    }

    private val _response = MutableLiveData<List<Web>>()
    val response: LiveData<List<Web>>
        get() = _response


    private fun getPostsProperties() {
        CoroutineScope(Job() + Dispatchers.Main).launch {
            var getPropertiesDeferred =
                    Albums.RETROFIT_SERVICE.getProperties()
            try {
                var listResult = getPropertiesDeferred.await()
                _response.value = listResult
            } catch (e: Exception) {
                _response.value = emptyList()
            }
        }
    }


}