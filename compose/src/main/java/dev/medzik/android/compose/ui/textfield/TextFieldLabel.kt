package dev.medzik.android.compose.ui.textfield

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
internal fun TextFieldLabel(
    modifier: Modifier = Modifier,
    text: String,
    expanded: Boolean,
    hasFocus: Boolean,
    hasError: Boolean,
) {
    val expandedTextSize = MaterialTheme.typography.bodySmall.fontSize.value
    val normalTextSize = LocalTextStyle.current.fontSize.value
    val textSize by animateFloatAsState(
        targetValue = if (expanded) expandedTextSize else normalTextSize,
        label = "TextFieldLabel text size",
    )

    val expandedTextColor = when {
        hasError -> MaterialTheme.colorScheme.error
        hasFocus -> MaterialTheme.colorScheme.primary
        else -> LocalContentColor.current
    }
    val normalTextColor = LocalContentColor.current
    val textColor by animateColorAsState(
        targetValue = if (expanded) expandedTextColor else normalTextColor,
        label = "TextFieldLabel text color"
    )

    Text(
        modifier = modifier,
        text = text,
        fontSize = textSize.sp,
        maxLines = 1,
        color = textColor,
    )
}
