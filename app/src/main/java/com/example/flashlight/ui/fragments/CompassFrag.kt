package com.example.flashlight.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.flashlight.databinding.FragmentCompassBinding


class CompassFrag : Fragment() {
    private var _binding : FragmentCompassBinding ?= null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentCompassBinding.inflate(inflater,container,false)

        return binding.root

    }

}