package com.benoitmanhes.cacheautresor.common.composable.input

import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.text.input.ImeAction

abstract class InputImeAction(
    val imeAction: ImeAction,
) {
    abstract val keyboardActions: KeyboardActions

    class Next(currentFocus: FocusManager, private val nextFocus: FocusRequester) : InputImeAction(ImeAction.Next) {
        override val keyboardActions: KeyboardActions = KeyboardActions(
            onNext = {
                currentFocus.clearFocus()
                nextFocus.requestFocus()
            }
        )
    }

    class Done(currentFocus: FocusManager, onDone: (KeyboardActionScope) -> Unit) : InputImeAction(ImeAction.Done) {
        override val keyboardActions: KeyboardActions = KeyboardActions(
            onDone = {
                currentFocus.clearFocus()
                onDone.invoke(this)
            }
        )
    }

    class Default(currentFocus: FocusManager, private val defaultAction: () -> Unit) : InputImeAction(ImeAction.Default) {
        override val keyboardActions: KeyboardActions = KeyboardActions {
            currentFocus.clearFocus()
            defaultAction()
        }
    }
}