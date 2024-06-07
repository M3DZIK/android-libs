package dev.medzik.android.components.ui

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * A composable function that displays an icon with a given image vector.
 *
 * @param imageVector the [ImageVector] to display for the icon
 * @param modifier the [Modifier] to apply to the icon
 */
@Composable
fun IconBox(
    imageVector: ImageVector,
    modifier: Modifier = Modifier
) = Icon(
    imageVector = imageVector,
    contentDescription = null,
    modifier = modifier
)
