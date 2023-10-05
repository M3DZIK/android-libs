package dev.medzik.android.composables.dialog

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun rememberDialogState() = remember { DialogState() }

/** A visibility controller for a dialog */
class DialogState {
    var isVisible by mutableStateOf(false)

    /** Show the dialog. */
    fun show() {
        isVisible = true
    }

    /** Hide the dialog. */
    fun hide() {
        isVisible = false
    }
}
