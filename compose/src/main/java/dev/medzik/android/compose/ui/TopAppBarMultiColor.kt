package dev.medzik.android.compose.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import dev.medzik.android.compose.icons.TopAppBarBackIcon

/**
 * [TopAppBar] with text with two different text colors.
 *
 * @param firstText the first text in the row
 * @param firstColor the [Color] of the first text
 * @param secondText the second text in the row
 * @param secondColor the [Color] of the second text
 * @param navigationIcon a lambda with navigation icon representation displayed at the start of the app bar
 * @param actions a lambda with actions displayed at the end of the app bar
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarMultiColor(
    firstText: String,
    firstColor: Color = MaterialTheme.colorScheme.secondary,
    secondText: String,
    secondColor: Color = MaterialTheme.colorScheme.primary,
    navigationIcon: @Composable (() -> Unit) = {},
    actions: @Composable (RowScope.() -> Unit) = {}
) {
    TopAppBar(
        title = {
            Row {
                Text(
                    text = firstText,
                    color = firstColor,
                    style = MaterialTheme.typography.titleLarge
                )

                Text(
                    text = secondText,
                    color = secondColor,
                    style = MaterialTheme.typography.titleLarge
                )
            }
        },
        navigationIcon = navigationIcon,
        actions = actions
    )
}

@Preview
@Composable
private fun TopAppBarMultiColorPreview() {
    TopAppBarMultiColor(
        firstText = "First",
        secondText = "Second",
        navigationIcon = {
            TopAppBarBackIcon(navController = NavController(LocalContext.current))
        }
    )
}
