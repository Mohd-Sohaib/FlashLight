package com.example.flashlight

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.flashlight.databinding.FragmentBottomSheetExitBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class BottomSheetExitFrag : BottomSheetDialogFragment() {
    private var _binding : FragmentBottomSheetExitBinding?=null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentBottomSheetExitBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imgTap.setOnClickListener {
            activity?.finish()
            activity?.moveTaskToBack(true)
        }
    }
}