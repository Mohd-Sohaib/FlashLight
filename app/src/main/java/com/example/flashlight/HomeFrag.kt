package com.example.flashlight

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.flashlight.databinding.FragmentHomeBinding


class HomeFrag : Fragment() {
    private var _binding : FragmentHomeBinding ?= null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnOn.visibility = View.INVISIBLE
        binding.imgLightOn.visibility = View.INVISIBLE
        binding.txtTorchOn.visibility = View.INVISIBLE

        binding.btnOff.setOnClickListener {
            binding.btnOff.visibility = View.INVISIBLE
            binding.btnOn.visibility = View.VISIBLE
            binding.imgLightOff.visibility = View.INVISIBLE
            binding.imgLightOn.visibility = View.VISIBLE
            binding.txtTorchOff.visibility = View.INVISIBLE
            binding.txtTorchOn.visibility = View.VISIBLE
        }

        binding.btnOn.setOnClickListener {
            binding.btnOn.visibility = View.INVISIBLE
            binding.btnOff.visibility = View.VISIBLE
            binding.imgLightOn.visibility = View.INVISIBLE
            binding.imgLightOff.visibility = View.VISIBLE
            binding.txtTorchOn.visibility = View.INVISIBLE
            binding.txtTorchOff.visibility = View.VISIBLE
        }

        binding.imgCompass.setOnClickListener {
            findNavController().navigate(R.id.action_homeFrag_to_compassFrag)
        }
    }

}