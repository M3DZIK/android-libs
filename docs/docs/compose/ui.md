# UI

## Dialog

### BaseDialog

Composable of a basic dialog.
[Javadoc documentation][BaseDialog javadoc]

Example:

![BaseDialog preview]

```kotlin
@Composable
fun BaseDialogExample() {
    val state = rememberDialogState()
    state.show()

    BaseDialog(state) {
        Column {
            Text(
                text = "Example Dialog",
                fontWeight = FontWeight.Black,
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .padding(bottom = 8.dp)
            )

            Box(
                modifier = Modifier.padding(horizontal = 24.dp)
            ) {
                Text("Some text")
            }
        }
    }
}
```

### PickerDialog

Dialog with a list of elements to select.
[Javadoc documentation][PickerDialog javadoc]

Example:

![PickerDialog preview]

```kotlin
@Composable
fun PickerDialogExample() {
    val state = rememberDialogState()
    state.show()

    val items = listOf("First", "Second", "Third")

    PickerDialog(
        state,
        title = "Example Picker Dialog",
        items = items,
        onSelected = {}
    ) {
        Text(
            text = it,
            modifier = Modifier
                .padding(vertical = 12.dp)
                .fillMaxWidth()
        )
    }
}
```

[BaseDialog preview]: https://github.com/M3DZIK/android-utils/assets/87065584/91156b20-7eac-48e5-9909-96c3d64f50fc
[PickerDialog preview]: https://github.com/M3DZIK/android-utils/assets/87065584/c35f0c4a-21e2-4a82-b5dd-4f9be93a0990

[BaseDialog javadoc]: https://www.javadoc.io/doc/dev.medzik.android/compose/latest/compose/dev.medzik.android.compose.ui.dialog/-base-dialog.html
[PickerDialog javadoc]: https://www.javadoc.io/doc/dev.medzik.android/compose/latest/compose/dev.medzik.android.compose.ui.dialog/-picker-dialog.html

## Preference

### BasicPreference

Basic preference entry.
[Javadoc documentation][BasicPreference javadoc]

Example:

![BasicPreference preview]

```kotlin
@Composable
fun BasicPreferenceExample() {
    BasicPreference(
        title = "Settings",
        subtitle = "Switch to settings screen",
        onClick = { println("Clicked!") },
        leading = {
            IconBox(Icons.Default.Settings)
        },
        trailing = {
            IconBox(Icons.Default.ArrowOutward)
        }
    )
}
```

### PreferenceGroupTitle

Title component for a group of preferences.
[Javadoc documentation][PreferenceGroupTitle javadoc]

Example:

![PreferenceGroupTitle preview]

```kotlin
@Composable
fun PreferenceGroupTitleExample() {
    Column {
        PreferenceGroupTitle("Group title")

        SwitcherPreference(
            title = "Switcher Preference",
            checked = false,
            onCheckedChange = {}
        )
    }
}
```

### PropertyPreference

Preference entry with a property.
[Javadoc documentation][PropertyPreference javadoc]

Example:

![PropertyPreference preview]

```kotlin
@Composable
fun PropertyPreferenceExample() {
    val propertyItems = listOf("First", "Second", "Third")
    val currentItem by rememberMutable(propertyItems[0])

    PropertyPreference(
        title = "Property title",
        subtitle = "Property subtitle",
        leading = {
            IconBox(Icons.Default.Lock)
        },
        currentValue = currentItem,
        onClick = { state.show() }
    )
}
```

### SwitcherPreference

Preference entry with a switcher.
[Javadoc documentation][SwitcherPreference javadoc]

Example:

![SwitcherPreference preview]

```kotlin
@Composable
fun SwitcherPreferenceExample() {
    val checked = rememberMutable(false)

    SwitcherPreference(
        title = "Switcher Preference",
        checked = checked
    )
}
```

[BasicPreference preview]: https://github.com/M3DZIK/android-utils/assets/87065584/031c99e5-e698-41ae-86bd-a20448b6f1fe
[PreferenceGroupTitle preview]: https://github.com/M3DZIK/android-utils/assets/87065584/68d7e152-1058-41b3-b275-83e74cc91391
[PropertyPreference preview]: https://github.com/M3DZIK/android-utils/assets/87065584/401e3a8f-8db9-452b-af81-4b23220723dc
[SwitcherPreference preview]: https://github.com/M3DZIK/android-utils/assets/87065584/b8b1f428-15d2-499a-bdda-138e01900fb2

[BasicPreference javadoc]: https://www.javadoc.io/doc/dev.medzik.android/compose/latest/compose/dev.medzik.android.compose.ui.preference/-basic-preference.html
[PreferenceGroupTitle javadoc]: https://www.javadoc.io/doc/dev.medzik.android/compose/latest/compose/dev.medzik.android.compose.ui.preference/-preference-group-title.html
[PropertyPreference javadoc]: https://www.javadoc.io/doc/dev.medzik.android/compose/latest/compose/dev.medzik.android.compose.ui.preference/-property-preference.html
[SwitcherPreference javadoc]: https://www.javadoc.io/doc/dev.medzik.android/compose/latest/compose/dev.medzik.android.compose.ui.preference/-switcher-preference.html
