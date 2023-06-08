package com.example.flashlight

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.graphics.Camera
import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraManager
import android.hardware.camera2.CameraManager.TorchCallback
import android.os.BatteryManager
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.getSystemService
import androidx.navigation.fragment.findNavController
import com.example.flashlight.databinding.FragmentHomeBinding
import java.lang.reflect.Parameter


class HomeFrag : Fragment() {
    private var _binding : FragmentHomeBinding ?= null
    private val binding get() = _binding!!

    private lateinit var cameraManager: CameraManager
    private lateinit var cameraId : String
    private lateinit var batteryManager: BatteryManager
    private lateinit var torchCallback: TorchCallback


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater,container,false)

        val isFlashAvailable = requireContext().packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)
        if(!isFlashAvailable){
           Toast.makeText(requireContext(),"Flash Not found...!", Toast.LENGTH_SHORT).show()
        }


        batteryManager = activity?.getSystemService(Context.BATTERY_SERVICE) as BatteryManager

        cameraManager = activity?.getSystemService(Context.CAMERA_SERVICE) as CameraManager
        try {
            cameraId = cameraManager.cameraIdList[0]
        }catch (e : CameraAccessException){
            e.printStackTrace()
        }
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnOn.visibility = View.INVISIBLE
        binding.imgLightOn.visibility = View.INVISIBLE
        binding.txtTorchOn.visibility = View.INVISIBLE
        binding.line1.visibility = View.INVISIBLE
        binding.line2.visibility = View.INVISIBLE
        binding.line3.visibility = View.INVISIBLE
        binding.line4.visibility = View.INVISIBLE
        binding.line5.visibility = View.INVISIBLE

        binding.btnOff.setOnClickListener {
            binding.btnOff.visibility = View.INVISIBLE
            binding.btnOn.visibility = View.VISIBLE
            binding.imgLightOff.visibility = View.INVISIBLE
            binding.imgLightOn.visibility = View.VISIBLE
            binding.txtTorchOff.visibility = View.INVISIBLE
            binding.txtTorchOn.visibility = View.VISIBLE
            flashLightOFF()
        }

        binding.btnOn.setOnClickListener {
            binding.btnOn.visibility = View.INVISIBLE
            binding.btnOff.visibility = View.VISIBLE
            binding.imgLightOn.visibility = View.INVISIBLE
            binding.imgLightOff.visibility = View.VISIBLE
            binding.txtTorchOn.visibility = View.INVISIBLE
            binding.txtTorchOff.visibility = View.VISIBLE
            flashLightON()
        }

        binding.imgCompass.setOnClickListener {
            findNavController().navigate(R.id.action_homeFrag_to_compassFrag)
        }

        val percentage = batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
        binding.txtBatteryPercentage.text = "$percentage%"
        if(percentage <= 20){
            binding.line1.visibility = View.VISIBLE
        }else if(percentage < 45){
            binding.line1.visibility = View.VISIBLE
            binding.line2.visibility = View.VISIBLE
        }else if(percentage < 65){
            binding.line1.visibility = View.VISIBLE
            binding.line2.visibility = View.VISIBLE
            binding.line3.visibility = View.VISIBLE
        }else if(percentage <= 90){
            binding.line1.visibility = View.VISIBLE
            binding.line2.visibility = View.VISIBLE
            binding.line3.visibility = View.VISIBLE
            binding.line4.visibility = View.VISIBLE
        }else{
            binding.line1.visibility = View.VISIBLE
            binding.line2.visibility = View.VISIBLE
            binding.line3.visibility = View.VISIBLE
            binding.line4.visibility = View.VISIBLE
            binding.line5.visibility = View.VISIBLE
        }
    }

  
    private fun flashLightON() {
       try {
           cameraManager.setTorchMode(cameraId,false)
       }catch (e : CameraAccessException){
           e.printStackTrace()
       }
    }

    private fun flashLightOFF() {
        try {
            cameraManager.setTorchMode(cameraId,true)
        }catch (e : CameraAccessException){
            e.printStackTrace()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null

        try {
            cameraManager.setTorchMode(cameraId,false)
        }catch (e : CameraAccessException){
            e.printStackTrace()
        }

    }
}