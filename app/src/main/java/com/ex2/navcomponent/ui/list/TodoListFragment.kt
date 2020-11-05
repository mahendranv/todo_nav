package com.ex2.navcomponent.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ex2.navcomponent.data.ToDoRepository
import com.ex2.navcomponent.databinding.FragmentToDoListBinding
import com.ex2.navcomponent.titleTransitionName

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

        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }

        binding.todoRecyclerView.adapter = TodoListAdapter().apply {
            setItems(ToDoRepository.list)
            setItemClickListener { todo, textView ->
                val extras = FragmentNavigatorExtras(
                    textView to todo.titleTransitionName()
                )
                val navigation =
                    TodoListFragmentDirections.actionTodoListFragmentToDetailsFragment(todo)
                findNavController().navigate(navigation, extras)
            }
        }
        binding.todoRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }
}