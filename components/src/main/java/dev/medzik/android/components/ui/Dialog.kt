package dev.medzik.android.components.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/** Remembers the state of dialog controller visibility. */
@Composable
fun rememberDialogState() = remember { DialogState() }

/** Visibility controller for dialogs. */
class DialogState {
    var isVisible by mutableStateOf(false)

    fun show() {
        isVisible = true
    }

    fun hide() {
        isVisible = false
    }
}

/**
 * Basic dialog composable.
 *
 * @param state visibility state
 * @param content dialog content to be displayed
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseDialog(
    state: DialogState,
    content: @Composable () -> Unit
) {
    if (state.isVisible) {
        BasicAlertDialog(
            onDismissRequest = { state.hide() }
        ) {
            Surface(
                shape = AlertDialogDefaults.shape,
                tonalElevation = AlertDialogDefaults.TonalElevation,
            ) {
                Box(
                    modifier = Modifier.padding(vertical = 24.dp)
                ) {
                    content()
                }
            }
        }
    }
}

@Preview
@Composable
fun BaseDialogPreview() {
    val state = rememberDialogState()
    state.show()

    Surface {
        BaseDialog(state) {
            Column {
                Text(
                    text = "Example Dialog",
                    fontWeight = FontWeight.Black,
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
                        .padding(bottom = 8.dp)
                )

                Box(
                    modifier = Modifier.padding(horizontal = 24.dp)
                ) {
                    Text("Some text")
                }
            }
        }
    }
}

/**
 * Picker dialog with a list of items.
 *
 * @param state visibility state
 * @param title title to display at the top of the dialog
 * @param items list of items to be displayed in the dialog
 * @param onSelected callback function to be called when an item is selected
 * @param content defines the visual representation of each item in the picker
 */
@Composable
fun <T> PickerDialog(
    state: DialogState,
    title: String?,
    items: List<T>,
    onSelected: (T) -> Unit,
    content: @Composable (T) -> Unit
) {
    BaseDialog(state) {
        Column {
            if (title != null) {
                Text(
                    text = title,
                    fontWeight = FontWeight.Black,
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
                        .padding(bottom = 8.dp)
                )
            }

            items.forEach { item ->
                Box(
                    modifier = Modifier.clickable {
                        onSelected(item)
                        state.hide()
                    }
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 24.dp)
                    ) {
                        content(item)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PickerDialogPreview() {
    val state = rememberDialogState()
    state.show()

    val items = listOf("First", "Second", "Third")

    Surface {
        PickerDialog(
            state,
            title = "Example Picker Dialog",
            items,
            onSelected = {}
        ) {
            Text(
                text = it,
                modifier =
                    Modifier
                        .padding(vertical = 12.dp)
                        .fillMaxWidth()
            )
        }
    }
}
