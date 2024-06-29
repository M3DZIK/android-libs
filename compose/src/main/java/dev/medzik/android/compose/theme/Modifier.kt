package dev.medzik.android.compose.theme

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

/**
 * Applies compact horizontal padding to the element,
 * using the small spacing value from [MaterialTheme.spacing].
 */
@Composable
fun Modifier.compactHorizontalPadding() = this then padding(
    horizontal = MaterialTheme.spacing.small
)

/**
 * Applies regular horizontal padding to the element,
 * using the medium spacing value from [MaterialTheme.spacing].
 */
@Composable
fun Modifier.regularHorizontalPadding() = this then padding(
    horizontal = MaterialTheme.spacing.medium
)

@Preview
@Composable
fun HorizontalPaddingPreview() {
    Surface {
        Column {
            Box(
                modifier = Modifier.compactHorizontalPadding()
            ) {
                Text(
                    text = "Compact horizontal padding"
                )
            }

            Box(
                modifier = Modifier.regularHorizontalPadding()
            ) {
                Text(
                    text = "Regular horizontal padding"
                )
            }
        }
    }
}
