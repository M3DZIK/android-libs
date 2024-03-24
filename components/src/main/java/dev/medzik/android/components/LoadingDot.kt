package dev.medzik.android.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * The dot that is used in [LoadingIndicator].
 *
 * @param color the [Color] of the dot
 * @param modifier the [Modifier] to be applied to the dot
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

@Preview
@Composable
fun LoadingDotPreview() {
    LoadingDot(
        color = MaterialTheme.colorScheme.primary,
        modifier =
            Modifier
                .width(8.dp)
                .aspectRatio(1f)
    )
}
