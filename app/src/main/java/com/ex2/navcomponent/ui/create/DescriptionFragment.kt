package com.ex2.navcomponent.ui.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ex2.navcomponent.*
import com.ex2.navcomponent.data.ToDoRepository
import com.ex2.navcomponent.databinding.FragmentCreateDescriptionBinding

class DescriptionFragment : Fragment() {

    private lateinit var binding: FragmentCreateDescriptionBinding

    private val args: DescriptionFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateDescriptionBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pickedColor =
            getNavResult<Int>(ColorPickerFragment.PICKED_COLOR) ?: ToDoRepository.COLORS[0]
        binding.colorCta.apply {
            fillColor = pickedColor
        }

        binding.titleLabel.text = args.title
        binding.descriptionTil.showKeyboard()
        binding.saveCta.setOnClickListener {
            binding.descriptionTil.hideKeyboard()
            quickSave(pickedColor)
            findNavController().popBackStack(R.id.todoListFragment, false)
        }

        binding.colorCta.setOnClickListener {
            val nav =
                DescriptionFragmentDirections.actionCreateDescriptionFragmentToColorPickerFragment()
            findNavController().navigate(nav)
            binding.descriptionTil.hideKeyboard()
        }
    }

    private fun quickSave(color: Int) {
        ToDoRepository.addToDo(args.title, binding.descriptionTil.getTrimmedText(), color)
    }

}