package com.example.flashlight

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class BottomSheetRatingFrag : BottomSheetDialogFragment(){

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bottom_sheet_rating, container, false)
    }

//    override fun onStart() {
//        super.onStart()
//        BottomSheetBehavior.from(requireView().parent as View).apply {
//            state = BottomSheetBehavior.STATE_HALF_EXPANDED
//        }
//    }
}