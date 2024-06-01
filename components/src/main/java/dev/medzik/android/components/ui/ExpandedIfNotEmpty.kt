package dev.medzik.android.components.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntSize
import dev.medzik.android.components.rememberMutable

@Composable
fun <T> ExpandedIfNotEmpty(
    value: T?,
    modifier: Modifier = Modifier,
    enter: EnterTransition = fadeIn() + expandIn(
        initialSize = { IntSize(it.width, 0) },
    ),
    exit: ExitTransition = shrinkOut(
        targetSize = { IntSize(it.width, 0) },
    ) + fadeOut(),
    content: @Composable (T) -> Unit
) {
    AnimatedVisibility(
        modifier = modifier,
        visible = value != null,
        enter = enter,
        exit = exit,
    ) {
        val rememberedValue by rememberMutable(value)
        content(rememberedValue!!)
    }
}
