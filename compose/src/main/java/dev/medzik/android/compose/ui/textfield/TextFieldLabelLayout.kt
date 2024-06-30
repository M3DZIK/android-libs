package dev.medzik.android.compose.ui.textfield

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import kotlin.math.max
import kotlin.math.roundToInt

@Composable
internal fun TextFieldLabelLayout(
    modifier: Modifier = Modifier,
    expanded: Boolean,
    content: @Composable () -> Unit,
) {
    val progress by animateFloatAsState(
        targetValue = if (expanded) 1f else 0f,
        label = "TextFieldLabelLayoutExpandProgress"
    )

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        Layout(
            content = content,
        ) { measurables, constraints ->
            val itemConstraints = constraints.copy(
                minHeight = 0,
            )

            val placeables = measurables.map { measurable ->
                measurable.measure(itemConstraints)
            }

            val width = constraints.maxWidth
            val height = kotlin.run {
                val maxHeight = placeables.maxOf { it.height }

                if (placeables.size == 2) {
                    val (label, field) = placeables
                    val height = (label.height * progress).roundToInt() + field.height
                    max(height, maxHeight)
                } else {
                    maxHeight
                }
            }

            layout(width, height) {
                if (placeables.size == 2) {
                    val (label, field) = placeables
                    label.placeRelative(0, 0)
                    field.placeRelative(0, (label.height * progress).roundToInt())
                } else {
                    placeables
                        .first()
                        .placeRelative(0, 0)
                }
            }
        }
    }
}
