package com.ex2.navcomponent.ui

import android.os.Bundle
import android.view.*
import android.view.animation.AnimationUtils
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.transition.Transition
import androidx.transition.TransitionInflater
import com.ex2.navcomponent.R
import com.ex2.navcomponent.data.ToDoRepository
import com.ex2.navcomponent.databinding.FragmentTodoDetailsBinding
import com.ex2.navcomponent.titleTransitionName
import com.google.android.material.snackbar.Snackbar

class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentTodoDetailsBinding

    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)

        val transition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move).apply {
                duration = DURATION_TRANSITION
            }.addListener(object : SimpleTransitionListener() {
                override fun onTransitionEnd(transition: Transition) {
                    settleOtherElements()
                }
            })
        sharedElementEnterTransition = transition
        sharedElementReturnTransition = transition
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_todo_details, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val todo = args.todoItem
        when (item.itemId) {
            R.id.actionComplete -> {
                ToDoRepository.markAsCompleted(todo.id, true)
                findNavController().popBackStack()
            }
            R.id.actionDelete -> {
                ToDoRepository.deleteTodo(todo.id)
                Snackbar.make(binding.root, "Todo item Deleted", Snackbar.LENGTH_SHORT).show()
                findNavController().popBackStack()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun settleOtherElements() {
        binding.statusToggle.apply {
            visibility = View.VISIBLE
            startAnimation(
                AnimationUtils.loadAnimation(context, R.anim.settle_in)
                    .also { it.duration = DURATION_SETTLE_IN })
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTodoDetailsBinding.inflate(layoutInflater, container, false)
        binding.descriptionLabel.transitionName = args.todoItem.titleTransitionName()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }

        val todoItem = args.todoItem
        binding.descriptionLabel.text = todoItem.description

        with(binding) {

            descriptionLabel.text = todoItem.description

            statusToggle.check(
                if (todoItem.completed)
                    R.id.itemCompleted
                else
                    R.id.itemPending
            )

            statusToggle.addOnButtonCheckedListener { _, checkedId, isChecked ->
                if (checkedId == R.id.itemCompleted) {
                    ToDoRepository.markAsCompleted(todoItem.id, true)
                } else if (checkedId == R.id.itemPending) {
                    ToDoRepository.markAsCompleted(todoItem.id, false)
                }
            }
        }
    }

    companion object {

        const val DURATION_SETTLE_IN = 200L
        const val DURATION_TRANSITION = 300L

    }
}