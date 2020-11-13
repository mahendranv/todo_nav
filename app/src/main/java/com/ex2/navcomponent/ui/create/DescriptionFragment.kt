package com.ex2.navcomponent.ui.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ex2.navcomponent.databinding.FragmentCreateDescriptionBinding
import com.ex2.navcomponent.getTrimmedText
import com.ex2.navcomponent.hideKeyboard
import com.ex2.navcomponent.showKeyboard

class DescriptionFragment : Fragment() {

    private lateinit var binding: FragmentCreateDescriptionBinding

    private val args: DescriptionFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateDescriptionBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.titleLabel.text = args.title
        binding.descriptionTil.showKeyboard()
        binding.nextCta.setOnClickListener {
            val nav = DescriptionFragmentDirections.actionCreateDescriptionFragmentToColorPickerFragment(
                title = args.title,
                description = binding.descriptionTil.getTrimmedText()
            )
            findNavController().navigate(nav)
            binding.descriptionTil.hideKeyboard()
        }
    }

}