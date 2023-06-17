package com.example.flashlight.ui.viewModel

import android.hardware.camera2.CameraManager
import android.os.BatteryManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class mainViewModel : ViewModel() {

    private val _percentage =  MutableLiveData<Int>()
    val percentage : LiveData<Int> = _percentage

}