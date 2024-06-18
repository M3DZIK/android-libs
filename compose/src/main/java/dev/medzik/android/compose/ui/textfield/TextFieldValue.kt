package dev.medzik.android.compose.ui.textfield

import androidx.compose.runtime.MutableState

data class TextFieldValue(
    val value: String = "",
    val onChange: ((String) -> Unit) = {},
    val editable: Boolean = true,
    val hint: String? = null,
    val error: String? = null,
    val valueLabel: ValueLabel? = null
) {
    companion object {
        fun fromMutableState(
            state: MutableState<String>,
            hint: String? = null,
            error: String? = null,
            valueLabel: ValueLabel? = null
        ): TextFieldValue {
            return TextFieldValue(
                value = state.value,
                onChange = state::value::set,
                hint = hint,
                error = error,
                valueLabel = valueLabel
            )
        }
    }

    data class ValueLabel(
        val type: Type,
        val text: String
    ) {
        enum class Type {
            SUCCESS,
            INFO,
            WARNING,
            ERROR
        }
    }
}
