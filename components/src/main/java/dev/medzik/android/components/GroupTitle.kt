package dev.medzik.android.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

/**
 * A composable function that displays a title for a group of items.
 *
 * @param text the text to display
 * @param modifier optional [Modifier] for the title
 */
@Composable
fun GroupTitle(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.primary,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GroupTitlePreview() {
    GroupTitle(
        text = "Group Title"
    )
}
