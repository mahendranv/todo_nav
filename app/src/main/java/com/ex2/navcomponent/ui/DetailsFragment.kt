package com.ex2.navcomponent.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ex2.navcomponent.databinding.FragmentTodoDetailsBinding

class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentTodoDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTodoDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}