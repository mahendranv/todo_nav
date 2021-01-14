package com.ex2.navcomponent.ui.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.ex2.navcomponent.R
import com.ex2.navcomponent.databinding.FragmentColorPickerBinding
import com.ex2.navcomponent.setNavResult

class ColorPickerFragment : Fragment() {

    private lateinit var binding: FragmentColorPickerBinding

    private val colorAdapter = ColorPickerAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentColorPickerBinding.inflate(layoutInflater, container, false)
        binding.colorPicker.apply {
            layoutManager = GridLayoutManager(context, 6)
            adapter = colorAdapter
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.saveCta.setOnClickListener {
            setNavResult(PICKED_COLOR, colorAdapter.selectedColor)
            findNavController().popBackStack()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_save) {
            findNavController().popBackStack()
            return true
        }
        return super.onOptionsItemSelected(item)
    }


    companion object {

        const val PICKED_COLOR = "picked_color"
    }
}