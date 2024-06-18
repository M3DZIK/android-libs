package dev.medzik.android.compose.ui.preference

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.medzik.android.compose.rememberMutable
import dev.medzik.android.compose.ui.IconBox
import dev.medzik.android.compose.ui.dialog.PickerDialog
import dev.medzik.android.compose.ui.dialog.rememberDialogState

/**
 * Displays a property preference.
 *
 * @param modifier the [Modifier] to be applied to the root element
 * @param title title of the preference entry
 * @param subtitle subtitle of the preference entry
 * @param currentValue the current value of the preference entry
 * @param onClick called when clicked the preference entry
 * @param leading composable to display with the preference entry
 * @param enabled indicates whether the preference entry is enabled or disabled
 */
@Composable
fun PropertyPreference(
    title: String,
    currentValue: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
    leading: @Composable (() -> Unit)? = null,
    enabled: Boolean = true
) = BasicPreference(
    modifier = modifier,
    title = title,
    subtitle = subtitle,
    onClick = onClick,
    leading = leading,
    trailing = {
        Text(
            text = currentValue,
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.padding(start = 16.dp)
        )
    },
    enabled = enabled
)

@Preview(showBackground = true)
@Composable
private fun PropertyPreferencePreview() {
    val propertyItems = listOf("First", "Second", "Third")
    var currentItem by rememberMutable(propertyItems[0])
    val state = rememberDialogState()

    Column {
        PropertyPreference(
            title = "Property title",
            subtitle = "Property subtitle",
            leading = {
                IconBox(imageVector = Icons.Default.Lock)
            },
            currentValue = currentItem,
            onClick = { state.show() }
        )

        PickerDialog(
            state,
            title = "Picker Dialog",
            items = propertyItems,
            onSelected = {
                currentItem = it
            }
        ) {
            Text(
                text = it,
                modifier = Modifier
                    .padding(vertical = 12.dp)
                    .fillMaxWidth()
            )
        }

        PropertyPreference(
            title = "Disabled",
            subtitle = "Disabled Property Preference",
            currentValue = "Value",
            onClick = {},
            enabled = false
        )
    }
}
