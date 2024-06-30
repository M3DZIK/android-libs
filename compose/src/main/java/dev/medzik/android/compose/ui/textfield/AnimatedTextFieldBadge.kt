package dev.medzik.android.compose.ui.textfield

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.unit.dp
import dev.medzik.android.compose.theme.infoContainer
import dev.medzik.android.compose.theme.isDark
import dev.medzik.android.compose.theme.spacing
import dev.medzik.android.compose.theme.successContainer
import dev.medzik.android.compose.theme.warningContainer

@Composable
internal fun AnimatedTextFieldBadge(
    modifier: Modifier = Modifier,
    badge: TextFieldValue.ValueLabel? = null,
    error: String? = null
) {
    val type = when {
        error != null -> TextFieldValue.ValueLabel.Type.ERROR
        badge != null -> badge.type
        else -> TextFieldValue.ValueLabel.Type.ERROR
    }

    AnimatedTextFieldBadge(
        modifier = modifier,
        type = type,
        text = error ?: badge?.text ?: "",
    )
}

@Composable
internal fun AnimatedTextFieldBadge(
    modifier: Modifier = Modifier,
    type: TextFieldValue.ValueLabel.Type,
    text: String
) {
    AnimatedTextFieldBadge(
        modifier = modifier.padding(
            top = 8.dp,
            bottom = 8.dp,
        ),
        backgroundColor = when (type) {
            TextFieldValue.ValueLabel.Type.SUCCESS -> MaterialTheme.colorScheme.successContainer
            TextFieldValue.ValueLabel.Type.INFO -> MaterialTheme.colorScheme.infoContainer
            TextFieldValue.ValueLabel.Type.WARNING -> MaterialTheme.colorScheme.warningContainer
            TextFieldValue.ValueLabel.Type.ERROR -> MaterialTheme.colorScheme.errorContainer
        },
        text = text
    )
}

@Composable
internal fun AnimatedTextFieldBadge(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    text: String
) {
    val backgroundColorState = animateColorAsState(
        backgroundColor,
        label = "TextFieldBadge background color"
    )

    val contentColor = run {
        val color = if (MaterialTheme.colorScheme.isDark) {
            Color.White
        } else {
            Color.Black
        }

        val tint = backgroundColor.copy(alpha = 0.1f)
        tint.compositeOver(color)
    }

    val contentColorState = animateColorAsState(
        contentColor,
        label = "TextFieldBadge content color"
    )

    Row(
        modifier = modifier
            .clip(MaterialTheme.shapes.small)
            .drawBehind {
                val color = backgroundColorState.value
                drawRect(color)
            }
            .padding(
                horizontal = MaterialTheme.spacing.small,
                vertical = MaterialTheme.spacing.extraSmall
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            modifier = Modifier.animateContentSize(),
            text = text,
            color = contentColorState.value,
            style = MaterialTheme.typography.labelSmall,
        )
    }
}
