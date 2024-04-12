package dev.medzik.android.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarMultiColor(
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
fun TopBarMultiColorPreview() {
    TopBarMultiColor(
        firstText = "First",
        secondText = "Second"
    )
}
