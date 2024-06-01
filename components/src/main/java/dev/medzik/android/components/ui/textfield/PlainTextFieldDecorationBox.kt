package dev.medzik.android.components.ui.textfield

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.VisualTransformation
import dev.medzik.android.components.Decoration

private const val PlaceholderAnimationDuration = 83
private const val PlaceholderAnimationDelayOrDuration = 67

@Composable
internal fun PlainTextFieldDecorationBox(
    modifier: Modifier,
    value: String,
    innerTextField: @Composable () -> Unit,
    placeholder: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    val transformedText = remember(value, visualTransformation) {
        visualTransformation.filter(AnnotatedString(value))
    }.text.text

    val isFocused = interactionSource.collectIsFocusedAsState().value
    val inputState = when {
        isFocused -> InputState.Focused
        transformedText.isEmpty() -> InputState.UnfocusedEmpty
        else -> InputState.UnfocusedNotEmpty
    }

    val transition = updateTransition(
        inputState,
        label = "TextField Input Phrase transitions"
    )

    val placeholderOpacity by transition.animateFloat(
        transitionSpec = {
            when {
                InputState.Focused isTransitioningTo InputState.UnfocusedEmpty -> {
                    tween(
                        durationMillis = PlaceholderAnimationDelayOrDuration,
                        easing = LinearEasing,
                    )
                }

                InputState.UnfocusedEmpty isTransitioningTo InputState.Focused ||
                InputState.UnfocusedNotEmpty isTransitioningTo InputState.UnfocusedEmpty -> {
                    tween(
                        durationMillis = PlaceholderAnimationDuration,
                        delayMillis = PlaceholderAnimationDelayOrDuration,
                        easing = LinearEasing,
                    )
                }

                else -> spring()
            }
        },
        label = "Placeholder opacity"
    ) {
        when (it) {
            InputState.Focused -> 1f
            InputState.UnfocusedEmpty, InputState.UnfocusedNotEmpty -> 0f
        }
    }

    val decoratedPlaceholder: @Composable (() -> Unit)? = when {
        placeholder != null && transformedText.isEmpty() -> {
            {
                Box(
                    modifier = Modifier.alpha(placeholderOpacity)
                ) {
                    Decoration(
                        contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        typography = MaterialTheme.typography.bodyLarge,
                        content = placeholder
                    )
                }
            }
        }

        else -> null
    }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.CenterStart,
    ) {
        if (decoratedPlaceholder != null)
            decoratedPlaceholder()

        Box(
            propagateMinConstraints = true
        ) {
            innerTextField()
        }
    }
}

/**
 * An internal state for animating a label and an indicator in text field.
 */
internal enum class InputState {
    /**
     * Text field is focused.
     */
    Focused,
    /**
     * Text field is not focused and input text is empty.
     */
    UnfocusedEmpty,
    /**
     * Text field is not focused but input text is not empty.
     */
    UnfocusedNotEmpty
}
