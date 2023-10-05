package dev.medzik.android.composables.dialog

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * A composable function for displaying a basic dialog.
 * @param state the state that controls visibility of the dialog
 * @param content the content of the dialog to display
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseDialog(
    state: DialogState,
    content: @Composable () -> Unit
) {
    if (state.isVisible) {
        AlertDialog(onDismissRequest = { state.hide() }) {
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

@Preview(showSystemUi = true)
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
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
                ) {
                    Text("Some text")
                }
            }
        }
    }
}
