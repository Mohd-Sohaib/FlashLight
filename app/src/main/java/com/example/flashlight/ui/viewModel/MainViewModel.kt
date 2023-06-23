package com.example.flashlight.ui.viewModel

import android.app.Application
import android.content.Context
import android.os.BatteryManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val _percentage =  MutableLiveData<Int>()
    fun percentage() : LiveData<Int>{
        return _percentage
    }

    fun getBatteryStatus() {
        val batteryManager: BatteryManager = getApplication<Application>()
            .getSystemService(Context.BATTERY_SERVICE) as BatteryManager

        _percentage.value =  batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
    }
}