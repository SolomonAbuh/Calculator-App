package com.example.calculator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class CoverterFragement : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_coverter_fragement, container, false)
        rootView.findViewById<TextView>(R.id.calculator_tv).setOnClickListener {
            val action = CoverterFragementDirections.actionCoverterFragementToCalculatorFragment2()
            findNavController().navigate(action)
        }


        return rootView
    }


}