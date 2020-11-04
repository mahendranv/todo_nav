package com.ex2.navcomponent.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ex2.navcomponent.data.ToDoRepository
import com.ex2.navcomponent.databinding.FragmentToDoListBinding

class TodoListFragment : Fragment() {

    private lateinit var binding: FragmentToDoListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentToDoListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.todoRecyclerView.adapter = TodoListAdapter().apply {
            setItems(ToDoRepository.list)
        }
        binding.todoRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }
}