package com.ex2.navcomponent.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.ex2.navcomponent.databinding.FragmentTodoDetailsBinding

class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentTodoDetailsBinding

    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTodoDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.descriptionLabel.text = args.todoItem.description

        with(binding) {
            descriptionLabel.text = args.todoItem.description
            completedStatus.text = "Completed: ${args.todoItem.completed}"
        }
    }
}