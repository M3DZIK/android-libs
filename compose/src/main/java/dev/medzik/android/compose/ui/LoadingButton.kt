package dev.medzik.android.compose.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.medzik.android.compose.rememberMutable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Button that shows a loading animation when [loading] is true.
 *
 * @param onClick called when the button is clicked
 * @param modifier the [Modifier] to be applied to the button
 * @param loading state of the loading animation, if true, the animation will be shown
 * @param enabled controls the enabled state of this button
 */
@Composable
fun LoadingButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    loading: Boolean,
    enabled: Boolean = true,
    content: @Composable () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled && !loading
    ) {
        if (loading) {
            LoadingIndicator(animating = true)
        } else {
            content()
        }
    }
}

/**
 * Button with an loading animation.
 *
 * @param onClick called when the button is clicked (launched on IO thread)
 * @param modifier the [Modifier] to be applied to the button
 * @param enabled controls the enabled state of this button
 */
@Composable
fun LoadingButton(
    onClick: suspend () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    content: @Composable () -> Unit
) {
    var loading by rememberMutable(false)
    val scope = rememberCoroutineScope()

    LoadingButton(
        onClick = {
            loading = true
            scope.launch(Dispatchers.IO) {
                onClick()
                loading = false
            }
        },
        modifier = modifier,
        loading = loading,
        enabled = enabled,
        content = content
    )
}

@Preview(showBackground = true)
@Composable
private fun LoadingButtonPreview() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var loading by rememberMutable(false)

        LoadingButton(
            loading = loading,
            onClick = { loading = !loading },
        ) {
            Text("Click me")
        }

        LoadingButton(
            loading = true,
            onClick = {},
        ) {
            Text("Loading")
        }

        LoadingButton(
            onClick = {
                Thread.sleep(1000)
            }
        ) {
            Text("Sleep 1s")
        }
    }
}
