package dev.medzik.android.compose.icons

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.medzik.android.compose.rememberMutable
import dev.medzik.android.compose.ui.IconBox

/**
 * Displays a visibility icon based on the provided boolean value.
 *
 * @param modifier the [Modifier] to be applied to the icon
 * @param visibility determines which icon should be displayed
 */
@Composable
fun VisibilityIcon(
    modifier: Modifier = Modifier,
    visibility: Boolean
) {
    val iconVector = when (visibility) {
        true -> Icons.Default.VisibilityOff
        false -> Icons.Default.Visibility
    }

    Crossfade(
        modifier = modifier,
        targetState = iconVector,
        label = "VisibilityIcon"
    ) { icon ->
        IconBox(imageVector = icon)
    }
}

@Preview
@Composable
private fun VisibilityIconPreview() {
    var visibility by rememberMutable(false)

    VisibilityIcon(
        modifier = Modifier.clickable {
            visibility = !visibility
        },
        visibility = visibility
    )
}