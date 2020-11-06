package com.ex2.navcomponent.ui.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ex2.navcomponent.databinding.FragmentCreateTodoBinding

class CreateTodoFragment : Fragment() {

    private lateinit var binding: FragmentCreateTodoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateTodoBinding.inflate(inflater, container, false)
        return binding.root
    }
}