package dev.medzik.android.components.ui

import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntSize

@Composable
fun <T> ExpandedIfNotEmptyRow(
    value: T?,
    modifier: Modifier = Modifier,
    content: @Composable (T) -> Unit
) = ExpandedIfNotEmpty(
    value = value,
    modifier = modifier,
    enter = fadeIn() + scaleIn() + expandIn(
        initialSize = { IntSize(0, it.height) },
    ),
    exit = shrinkOut(
        targetSize = { IntSize(0, it.height) },
    ) + fadeOut() + scaleOut(),
    content = content
)
