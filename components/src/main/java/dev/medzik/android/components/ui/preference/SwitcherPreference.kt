package dev.medzik.android.components.ui.preference

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.medzik.android.components.rememberMutable

/**
 * Displays a preference entry with a switcher.
 *
 * @param modifier the [Modifier] to be applied to the root element
 * @param title title of the preference entry
 * @param subtitle subtitle of the preference entry
 * @param checked current checked state
 * @param onCheckedChange called when changed the checked state or when clicked the preference entry
 * @param leading composable to display with the preference entry
 * @param enabled indicates whether the preference entry is enabled or disabled
 */
@Composable
fun SwitcherPreference(
    title: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
    leading: @Composable (() -> Unit)? = null,
    enabled: Boolean = true
) = BasicPreference(
    modifier = modifier,
    title = title,
    subtitle = subtitle,
    onClick = { onCheckedChange(!checked) },
    leading = leading,
    trailing = {
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
    },
    enabled = enabled
)

/**
 * Displays a preference entry with a switcher.
 *
 * @param modifier the [Modifier] to be applied to the root element
 * @param title title of the preference entry
 * @param subtitle subtitle of the preference entry
 * @param checked mutable state of a current checked state
 * @param onCheckedChange called when changed the checked state or when clicked the preference entry
 * @param leading composable to display with the preference entry
 * @param enabled indicates whether the preference entry is enabled or disabled
 */
@Composable
fun SwitcherPreference(
    title: String,
    checked: MutableState<Boolean>,
    modifier: Modifier = Modifier,
    onCheckedChange: (Boolean) -> Unit = {},
    subtitle: String? = null,
    leading: @Composable (() -> Unit)? = null,
    enabled: Boolean = true
) = SwitcherPreference(
    modifier = modifier,
    title = title,
    subtitle = subtitle,
    checked = checked.value,
    onCheckedChange = {
        checked.value = it
        onCheckedChange(it)
    },
    leading = leading,
    enabled = enabled
)

@Preview(showBackground = true)
@Composable
private fun SwitcherPreferencePreview() {
    val checked = rememberMutable(false)

    Column {
        SwitcherPreference(
            title = "Switcher Preference",
            checked = checked
        )

        SwitcherPreference(
            title = "Disabled",
            subtitle = "Disabled Switcher Preference",
            checked = true,
            onCheckedChange = {},
            enabled = false
        )
    }
}
