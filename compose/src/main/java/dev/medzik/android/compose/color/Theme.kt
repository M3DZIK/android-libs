package dev.medzik.android.compose.color

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver

private const val SUCCESS = 120f
private const val INFO = 210f
private const val WARNING = 55f

@Composable
private fun ColorScheme.buildContainerColor(hue: Float): Color {
    val tint = Color.hsv(
        hue = hue,
        saturation = 1f,
        value = 1f,
    )

    val backgroundColor = surfaceVariant

    return tint
        .copy(alpha = 0.20f)
        .compositeOver(backgroundColor)
}

/**
 * Returns the container color for success items.
 *
 * The color is typically used for backgrounds for a success badge.
 */
val ColorScheme.successContainer: Color
    @Composable
    get() = buildContainerColor(SUCCESS)

/**
 * Returns the container color for info items.
 *
 * The color is typically used for backgrounds for a info badge.
 */
val ColorScheme.infoContainer: Color
    @Composable
    get() = buildContainerColor(INFO)

/**
 * Returns the container color for warning items.
 *
 * The color is typically used for backgrounds for a warning badge.
 */
val ColorScheme.warningContainer: Color
    @Composable
    get() = buildContainerColor(WARNING)
