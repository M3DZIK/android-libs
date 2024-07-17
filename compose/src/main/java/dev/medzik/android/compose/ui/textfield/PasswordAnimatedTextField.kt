package dev.medzik.android.compose.ui.textfield

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Password
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import dev.medzik.android.compose.rememberMutable
import dev.medzik.android.compose.ui.IconBox

@Composable
fun PasswordAnimatedTextField(
    value: TextFieldValue,
    modifier: Modifier = Modifier,
    fieldModifier: Modifier = Modifier,
    boxModifier: Modifier = Modifier,
    placeholder: String? = null,
    label: String? = null,
    disabled: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    visibleVisualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    clearButton: Boolean = true,
    leading: (@Composable RowScope.() -> Unit)? = {
        IconBox(imageVector = Icons.Default.Password)
    },
    trailing: (@Composable RowScope.() -> Unit)? = null,
    content: (@Composable ColumnScope.() -> Unit)? = null
) {
    MaskedAnimatedTextField(
        modifier = modifier,
        fieldModifier = fieldModifier,
        boxModifier = boxModifier,
        value = value,
        placeholder = placeholder,
        label = label,
        disabled = disabled,
        textStyle = textStyle,
        visibilityVisualTransformation = visibleVisualTransformation,
        keyboardOptions = keyboardOptions.copy(
            autoCorrect = false,
            keyboardType = KeyboardType.Password,
        ),
        keyboardActions = keyboardActions,
        singleLine = true,
        maxLines = 1,
        interactionSource = interactionSource,
        clearButton = clearButton,
        leading = leading,
        trailing = trailing,
        content = content
    )
}

@Preview
@Composable
private fun PasswordAnimatedTextFieldPreview() {
    val password = rememberMutable { "Password" }

    MaterialTheme {
        PasswordAnimatedTextField(
            value = TextFieldValue.fromMutableState(
                state = password
            )
        )
    }
}
