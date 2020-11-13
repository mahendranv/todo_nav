package com.ex2.navcomponent.ui.create

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.ex2.navcomponent.R
import com.ex2.navcomponent.data.ToDoRepository
import com.ex2.navcomponent.databinding.FragmentColorPickerBinding

class ColorPickerFragment : Fragment() {

    private lateinit var binding: FragmentColorPickerBinding

    private val colorAdapter = ColorPickerAdapter()

    private val args by navArgs<ColorPickerFragmentArgs>()

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
            quickSave()
        }
    }

    private fun quickSave() {
        val todo = ToDoRepository.addToDo(args.title, args.description, colorAdapter.selectedColor)
        findNavController().popBackStack(R.id.todoListFragment, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
//        inflater.inflate(R.menu.menu_color_picker, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_save) {
            findNavController().popBackStack()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}