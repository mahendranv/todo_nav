package com.ex2.navcomponent.ui

import android.os.Bundle
import android.view.*
import android.view.animation.AnimationUtils
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.transition.Transition
import androidx.transition.TransitionInflater
import com.ex2.navcomponent.R
import com.ex2.navcomponent.databinding.FragmentTodoDetailsBinding
import com.ex2.navcomponent.titleTransitionName

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

    private fun settleOtherElements() {
        binding.completedStatus.apply {
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

        binding.descriptionLabel.text = args.todoItem.description

        with(binding) {
            descriptionLabel.text = args.todoItem.description
            completedStatus.text = "Completed: ${args.todoItem.completed}"
        }
    }

    companion object {

        const val DURATION_SETTLE_IN = 200L
        const val DURATION_TRANSITION = 300L

    }
}