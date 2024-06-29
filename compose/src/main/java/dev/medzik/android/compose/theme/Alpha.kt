package dev.medzik.android.compose.theme

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * The alpha value for enabled components.
 */
const val NormalAlpha = 1f

/**
 * The alpha value for disabled components.
 */
const val DisabledAlpha = 0.38f

@Preview
@Composable
private fun NormalAlphaPreview() {
    Surface(
        color = MaterialTheme.colorScheme.successContainer.combineAlpha(NormalAlpha),
        shape = MaterialTheme.shapes.large
    ) {
        Spacer(
            modifier = Modifier.padding(horizontal = 30.dp, vertical = 15.dp)
        )
    }
}

@Preview
@Composable
private fun DisabledAlphaPreview() {
    Surface(
        color = MaterialTheme.colorScheme.successContainer.combineAlpha(DisabledAlpha),
        shape = MaterialTheme.shapes.large
    ) {
        Spacer(
            modifier = Modifier.padding(horizontal = 30.dp, vertical = 15.dp)
        )
    }
}
