package com.ex2.navcomponent.ui

import androidx.transition.Transition

/**
 * A shell implementation of TransitionListener. This avoids overriding all the methods
 * throughout the app.
 */
open class SimpleTransitionListener : Transition.TransitionListener {

    override fun onTransitionEnd(transition: Transition) {
    }

    override fun onTransitionResume(transition: Transition) {
    }

    override fun onTransitionPause(transition: Transition) {
    }

    override fun onTransitionCancel(transition: Transition) {
    }

    override fun onTransitionStart(transition: Transition) {
    }
}