package dev.medzik.android.components.ui.dialog

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Composable of a picker dialog with a list of items.
 *
 * @param state visibility state
 * @param title title to display at the top of the dialog
 * @param items list of items to be displayed in the dialog
 * @param onSelected callback function to be called when an item is selected
 * @param trailing optionally adds a trailing row to the bottom of the dialog
 * @param content defines the visual representation of each item in the picker
 */
@Composable
fun <T> PickerDialog(
    state: DialogState,
    title: String? = null,
    items: List<T>,
    onSelected: (T) -> Unit,
    trailing: (@Composable RowScope.() -> Unit)? = null,
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

            if (trailing != null) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                ) {
                    trailing()
                }
            }
        }
    }
}

@Preview
@Composable
private fun PickerDialogPreview() {
    val state = rememberDialogState()
    state.show()

    val items = listOf("First", "Second", "Third")

    Surface {
        PickerDialog(
            state,
            title = "Example Picker Dialog",
            items = items,
            onSelected = {}
        ) {
            Text(
                text = it,
                modifier = Modifier
                    .padding(vertical = 12.dp)
                    .fillMaxWidth()
            )
        }
    }
}
