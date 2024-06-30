package dev.medzik.android.compose.theme

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Defines a set of spacing values for use in layouts.
 *
 * @property extraSmall The smallest spacing value, typically used for minimal padding or margins.
 * @property small A small spacing value, suitable for padding between element in a dense layout.
 * @property medium A standard spacing value, providing comfortable spacing between elements.
 * @property large A larger spacing value, used for more significant separation between elements.
 * @property mediumLarge The medium large spacing value, typically used for min height of a preference or text field.
 */
data class Spacing(
    val extraSmall: Dp = 4.dp,
    val small: Dp = 8.dp,
    val medium: Dp = 16.dp,
    val large: Dp = 32.dp,
    val mediumLarge: Dp = 50.dp
) {
    /**
     * The vertical padding for a dialog.
     */
    val dialog = 24.dp
    /**
     * Half of the vertical padding for a dialog.
     * Used for horizontal padding between elements in a dialog (for example in a picker dialog).
     */
    val dialogHalf = 12.dp

    /**
     * The padding after/before a leading/trailing icon.
     */
    val buttonIcon: Dp = 12.dp
}

/**
 * A CompositionLocal that provides access to the [Spacing] values.
 */
private val LocalSpacing = compositionLocalOf { Spacing() }

/**
 * An extension property on [MaterialTheme] that provides convenient access to the [Spacing] values.
 */
@Suppress("UnusedReceiverParameter")
val MaterialTheme.spacing: Spacing
    @Composable
    @ReadOnlyComposable
    get() = LocalSpacing.current

@Preview
@Composable
fun SpacingSample() {
    MaterialTheme {
        Surface {
            Column {
                Box(
                    modifier = Modifier
                        .border(1.dp, Color.Red)
                        .padding(MaterialTheme.spacing.medium)
                ) {
                    Text("Text with medium padding")
                }

                Box(
                    modifier = Modifier
                        .border(1.dp, Color.Green)
                        .padding(MaterialTheme.spacing.small)
                ) {
                    Text("Text with small padding")
                }

                Box(
                    modifier = Modifier
                        .border(1.dp, Color.Red)
                        .padding(MaterialTheme.spacing.large)
                ) {
                    Text("Text with large padding")
                }
            }
        }
    }
}
