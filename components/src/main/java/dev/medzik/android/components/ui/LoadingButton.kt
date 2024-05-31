package dev.medzik.android.components.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.medzik.android.components.rememberMutableBoolean

/**
 * Button with an option to show a loading animation.
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
    loading: Boolean = false,
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

@Preview(showBackground = true)
@Composable
fun LoadingButtonPreview() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var loading by rememberMutableBoolean(false)

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
    }
}
