package com.ex2.navcomponent.ui.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
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

        binding.titleEntry.showKeyboard()
        binding.saveCta.setOnClickListener {
            binding.titleEntry.hideKeyboard()

            // Pick color in next screen
            val title = binding.titleEntry.text.toString()
            findNavController().navigate(
                CreateTodoFragmentDirections.actionCreateTodoFragmentToCreateDescriptionFragment(
                    title = title
                )
            )
        }

//        binding.colorIcon.setOnClickListener {
//            findNavController().navigate(R.id.action_createTodoFragment_to_colorPickerFragment)
//        }
    }

    private fun quickSave() {
        val description = binding.titleEntry.text.toString()
        ToDoRepository.addToDo(description)
        findNavController().popBackStack()
        binding.titleEntry.hideKeyboard()
    }
}