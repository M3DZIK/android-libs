package dev.medzik.android.components

import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

/**
 * Sets content color, typography and emphasis for [content] composable.
 */
@Composable
fun Decoration(
    contentColor: Color,
    typography: TextStyle? = null,
    content: @Composable () -> Unit,
) {
    val contentWithColor: @Composable () -> Unit = @Composable {
        CompositionLocalProvider(
            LocalContentColor provides contentColor,
            content = content,
        )
    }

    if (typography != null) ProvideTextStyle(typography, contentWithColor) else contentWithColor()
}
