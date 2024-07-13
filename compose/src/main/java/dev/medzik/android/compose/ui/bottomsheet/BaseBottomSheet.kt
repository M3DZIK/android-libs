package dev.medzik.android.compose.ui.bottomsheet

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.medzik.android.compose.theme.spacing
import kotlinx.coroutines.launch

/**
 * Displays a basic bottom sheet.
 *
 * @param state visible state
 * @param onDismiss function that handles the dismissal of the bottom sheet
 * @param content content of the bottom sheet to display
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseBottomSheet(
    state: BottomSheetState,
    onDismiss: () -> Unit,
    content: @Composable (ColumnScope.() -> Unit)
) {
    if (state.visible) {
        ModalBottomSheet(
            onDismissRequest = onDismiss,
            windowInsets = WindowInsets(0, 0, 0, 0),
            sheetState = state.sheetState
        ) {
            Box(
                modifier = Modifier.navigationBarsPadding()
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    content()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun BaseBottomSheetPreview() {
    val scope = rememberCoroutineScope()
    val state = rememberBottomSheetState()

    Button(
        onClick = { scope.launch { state.show() } }
    ) {
        Text("Show")
    }

    BaseBottomSheet(
        state = state,
        onDismiss = {
            scope.launch { state.hide() }
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    scope
                        .launch { state.hide() }
                        .invokeOnCompletion {
                            if (!state.sheetState.isVisible) {
                                println("Clicked")
                            }
                        }
                }
                .height(44.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "Edit Profile",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Normal,
                fontSize = 15.sp,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MaterialTheme.spacing.horizontalPadding)
            )
        }
    }
}
