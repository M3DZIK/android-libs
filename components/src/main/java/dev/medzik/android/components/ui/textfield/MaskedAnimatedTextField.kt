package dev.medzik.android.components.ui.textfield

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import dev.medzik.android.components.TextFieldValue
import dev.medzik.android.components.icons.VisibilityIcon
import dev.medzik.android.components.rememberMutableBoolean

@Composable
fun MaskedAnimatedTextField(
    modifier: Modifier = Modifier,
    fieldModifier: Modifier = Modifier,
    boxModifier: Modifier = Modifier,
    value: TextFieldValue,
    placeholder: String? = null,
    label: String? = null,
    disabled: Boolean = false,
    readOnly: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    clearButton: Boolean = true,
    leading: (@Composable RowScope.() -> Unit)? = null,
    trailing: (@Composable RowScope.() -> Unit)? = null,
    content: (@Composable ColumnScope.() -> Unit)? = null
) {
    var visibility by rememberMutableBoolean()

    AnimatedTextField(
        modifier = modifier,
        fieldModifier = fieldModifier,
        boxModifier = boxModifier,
        value = value,
        placeholder = placeholder,
        label = label,
        disabled = disabled,
        readOnly = readOnly,
        textStyle = textStyle,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = singleLine,
        maxLines = maxLines,
        interactionSource = interactionSource,
        leading = leading,
        clearButton = clearButton,
        trailing = {
            IconButton(onClick = { visibility = !visibility }) {
                VisibilityIcon(visibility = visibility)
            }

            if (trailing != null) {
                trailing()
            }
        },
        visualTransformation = if (visibility) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        content = content
    )
}

@Preview
@Composable
fun MaskedAnimatedTextFieldPreview() {
    MaterialTheme {
        MaskedAnimatedTextField(
            value = TextFieldValue(
                value = "masked text",
                editable = false
            )
        )
    }
}
