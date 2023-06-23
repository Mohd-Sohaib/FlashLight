package com.example.flashlight.ui.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Color
import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraManager
import android.hardware.camera2.CameraManager.TorchCallback
import android.os.BatteryManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.flashlight.R
import com.example.flashlight.databinding.FragmentHomeBinding
import com.example.flashlight.ui.viewModel.MainViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class HomeFrag : BottomSheetDialogFragment() {
    private var _binding : FragmentHomeBinding ?= null
    private val binding get() = _binding!!

    private lateinit var cameraManager: CameraManager
    private lateinit var cameraId : String
    private lateinit var viewModel : MainViewModel
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

        //creating instance of our ViewModel
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.getBatteryStatus()

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
            binding.txtTorchStatus.visibility = View.VISIBLE
            binding.txtTorchStatus.text = "Torch On"
            binding.txtTorchStatus.setTextColor(resources.getColor(R.color.white))
            flashLightOFF()
        }

        binding.btnOn.setOnClickListener {
            binding.btnOn.visibility = View.INVISIBLE
            binding.btnOff.visibility = View.VISIBLE
            binding.imgLightOn.visibility = View.INVISIBLE
            binding.imgLightOff.visibility = View.VISIBLE
            binding.txtTorchStatus.visibility = View.VISIBLE
            binding.txtTorchStatus.text = "Torch Off"
            binding.txtTorchStatus.setTextColor(resources.getColor(R.color.off_color))
            flashLightON()
        }

        binding.imgCompass.setOnClickListener {
            findNavController().navigate(R.id.action_homeFrag_to_compassFrag)
        }

        //observing the percentage
        viewModel.percentage().observe(this, Observer {
            val percentage = it.toString()
            binding.txtBatteryPercentage.text = "$it%"

            if(percentage <= 20.toString()){
                binding.line1.visibility = View.VISIBLE
            }else if(percentage < 45.toString()){
                binding.line1.visibility = View.VISIBLE
                binding.line2.visibility = View.VISIBLE
            }else if(percentage < 65.toString()){
                binding.line1.visibility = View.VISIBLE
                binding.line2.visibility = View.VISIBLE
                binding.line3.visibility = View.VISIBLE
            }else if(percentage <= 90.toString()){
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
        })

        //controlling back-pressed
        requireActivity().onBackPressedDispatcher
            .addCallback(this, object : OnBackPressedCallback(true){
                override fun handleOnBackPressed() {
                    val bottomSheetExit = BottomSheetExitFrag()
                    bottomSheetExit.show(fragmentManager!!,bottomSheetExit.tag)
                }
            })

        binding.imgRate.setOnClickListener {
            val bottomSheetRating =  BottomSheetRatingFrag()
            bottomSheetRating.show(fragmentManager!!,bottomSheetRating.tag)
        }

        binding.imgShare.setOnClickListener {
            Toast.makeText(requireContext(),"Share Selected!!" , Toast.LENGTH_SHORT).show()
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