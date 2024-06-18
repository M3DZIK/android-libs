package dev.medzik.android.compose.ui.dialog

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

/**
 * Remembers the state of dialog controller visibility.
 */
@Composable
fun rememberDialogState() = remember { DialogState() }

/**
 * Visibility controller for dialogs.
 */
class DialogState {
    var isVisible by mutableStateOf(false)

    fun show() {
        isVisible = true
    }

    fun hide() {
        isVisible = false
    }
}
