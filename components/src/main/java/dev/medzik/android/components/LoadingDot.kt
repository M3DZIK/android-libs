package dev.medzik.android.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color

/**
 * Only the dot that is used in [LoadingIndicator].
 *
 * @param color The color of the dot.
 * @param modifier The modifier to apply to the dot.
 */
@Composable
fun LoadingDot(
    color: Color,
    modifier: Modifier = Modifier
) {
    Box(
        modifier =
        modifier
            .clip(CircleShape)
            .background(color)
    )
}
