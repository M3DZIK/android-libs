package dev.medzik.android.compose.ui.dialog

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.medzik.android.compose.theme.spacing

/**
 * Composable of a Basic dialog.
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
                    modifier = Modifier.padding(vertical = MaterialTheme.spacing.dialog)
                ) {
                    content()
                }
            }
        }
    }
}

@Preview
// #region snippet
@Composable
private fun BaseDialogPreview() {
    val state = rememberDialogState()
    state.show()

    Surface {
        BaseDialog(state) {
            Column {
                DialogTitle("Example Dialog")

                Box(
                    modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium)
                ) {
                    Text("Some text")
                }
            }
        }
    }
}
// #endregion snippet
