package dev.medzik.android.compose.color

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

private const val SUCCESS = 120f
private const val INFO = 200f
private const val WARNING = 55f

private fun ColorScheme.buildContainerColor(hue: Float): Color {
    val tint = Color.hsv(
        hue = hue,
        saturation = 1f,
        value = 1f,
        alpha = 0.20f
    )

    val backgroundColor = surfaceVariant

    return tint.compositeOver(backgroundColor)
}

/**
 * Returns `true` if the background color is dark, `false` otherwise.
 */
val ColorScheme.isDark
    get() = background.luminance() < 0.5f

/**
 * Returns the container color for success items.
 *
 * The color is typically used for backgrounds for a success badge.
 */
val ColorScheme.successContainer: Color
    get() = buildContainerColor(SUCCESS)

/**
 * Returns the container color for info items.
 *
 * The color is typically used for backgrounds for a info badge.
 */
val ColorScheme.infoContainer: Color
    get() = buildContainerColor(INFO)

/**
 * Returns the container color for warning items.
 *
 * The color is typically used for backgrounds for a warning badge.
 */
val ColorScheme.warningContainer: Color
    get() = buildContainerColor(WARNING)

@Preview
@Composable
private fun SuccessContainerPreview() {
    Surface(
        color = MaterialTheme.colorScheme.successContainer,
        shape = MaterialTheme.shapes.large
    ) {
        Spacer(
            modifier = Modifier.padding(horizontal = 30.dp, vertical = 15.dp)
        )
    }
}

@Preview
@Composable
private fun InfoContainerPreview() {
    Surface(
        color = MaterialTheme.colorScheme.infoContainer,
        shape = MaterialTheme.shapes.large
    ) {
        Spacer(
            modifier = Modifier.padding(horizontal = 30.dp, vertical = 15.dp)
        )
    }
}

@Preview
@Composable
private fun WarningContainerPreview() {
    Surface(
        color = MaterialTheme.colorScheme.warningContainer,
        shape = MaterialTheme.shapes.large
    ) {
        Spacer(
            modifier = Modifier.padding(horizontal = 30.dp, vertical = 15.dp)
        )
    }
}
