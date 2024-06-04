package dev.medzik.android.components.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

/**
 * Displays a title for a group of items.
 *
 * @param text The text to display.
 * @param modifier The modifier to be applied to the title.
 */
@Composable
fun GroupTitle(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        style = MaterialTheme.typography.labelLarge,
        color = MaterialTheme.colorScheme.primary,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
private fun GroupTitlePreview() {
    GroupTitle(
        text = "Group Title"
    )
}
