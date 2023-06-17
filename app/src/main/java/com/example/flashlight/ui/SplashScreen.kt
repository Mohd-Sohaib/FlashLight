package com.example.flashlight.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.WindowManager
import com.example.flashlight.databinding.ActivitySplashScreenBinding

@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {
    private var _binding : ActivitySplashScreenBinding ? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
            WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
        )

        binding.imgTorchLight.visibility = View.GONE
        binding.imgTextFlashLight.visibility = View.GONE

        binding.imgTorchLight.postDelayed({
            binding.imgTorchLight.visibility = View.VISIBLE
        },2000)
        binding.imgTextFlashLight.postDelayed({
            binding.imgTextFlashLight.visibility = View.VISIBLE
        },2000)


        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        },4000)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}