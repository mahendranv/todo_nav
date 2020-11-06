package com.ex2.navcomponent.ui.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ex2.navcomponent.R
import com.ex2.navcomponent.data.ToDoRepository
import com.ex2.navcomponent.databinding.FragmentCreateTodoBinding
import com.ex2.navcomponent.hideKeyboard
import com.ex2.navcomponent.showKeyboard

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.descriptionEntry.postDelayed({
            binding.descriptionEntry.showKeyboard()
        }, 200)

        binding.saveCta.setOnClickListener {
            binding.descriptionEntry.hideKeyboard()

            // Pick color in next screen
            val description = binding.descriptionEntry.text.toString()
            findNavController().navigate(
                CreateTodoFragmentDirections.actionCreateTodoFragmentToColorPickerFragment(
                    description
                )
            )
        }

//        binding.colorIcon.setOnClickListener {
//            findNavController().navigate(R.id.action_createTodoFragment_to_colorPickerFragment)
//        }
    }

    private fun quickSave() {
        val description = binding.descriptionEntry.text.toString()
        ToDoRepository.addToDo(description)
        findNavController().popBackStack()
        binding.descriptionEntry.hideKeyboard()
    }
}