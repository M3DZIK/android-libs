package dev.medzik.android.compose.ui.preference

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.medzik.android.compose.ui.GroupTitle

/**
 * Title component for a group of preferences.
 *
 * @param title title of the group
 * @param modifier the [Modifier] to be applied to the title
 */
@Composable
fun PreferenceGroupTitle(
    title: String,
    modifier: Modifier = Modifier
) = GroupTitle(
    text = title,
    modifier = modifier.padding(vertical = 8.dp, horizontal = 16.dp)
)
