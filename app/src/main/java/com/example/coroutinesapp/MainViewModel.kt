package com.example.coroutinesapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    var running = true

    var isRestart = false

    private var activeJob: Job? = null

    val counterPI = MutableLiveData<String>()

    var count = 0

    fun startPI() {
        running = true
        if (activeJob?.isActive == true) {
            stopPI()
        }
        activeJob = viewModelScope.launch(Dispatchers.Default) {
            while (running) {
                counterPI.postValue("3.14${count++}")
                delay(100)
            }
        }
    }

    fun stopPI() {
        running = false
        if (!running) {
            activeJob?.cancel()
            activeJob = null
        }
    }

}