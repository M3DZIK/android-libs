# Theme

Documentation of the `dev.medzik.android.compose.theme` package.

## Custom color schemas

| Name             | Preview                     |
|------------------|-----------------------------|
| successContainer | ![successContainer preview] |
| infoContainer    | ![infoContainer preview]    |
| warningContainer | ![warningContainer preview] |

```kotlin
@Composable
fun PreviewContainer() {
    Surface(
        color = MaterialTheme.colorScheme.successContainer, // infoContainer, warningContainer
        shape = MaterialTheme.shapes.large
    ) {
        Spacer(
            modifier = Modifier.padding(horizontal = 30.dp, vertical = 15.dp)
        )
    }
}
```

[successContainer preview]: https://github.com/M3DZIK/android-utils/assets/87065584/79a73850-f290-445f-af43-ec112d5af727
[infoContainer preview]: https://github.com/M3DZIK/android-utils/assets/87065584/3bcc2959-108f-41de-84d3-035a6af73389
[warningContainer preview]: https://github.com/M3DZIK/android-utils/assets/87065584/b5de56f2-0ec9-4672-b0af-77a8a2934143

## combineAlpha

The `combineAlpha(alpha: Float)` function returns a new color multiplied by the alpha value.

See the [alpha](#alpha) section for examples.

## Alpha

| Name          | Preview                  |
|---------------|--------------------------|
| NormalAlpha   | ![NormalAlpha preview]   |
| DisabledAlpha | ![DisabledAlpha preview] |

```kotlin
@Composable
private fun PreviewContainer() {
    Surface(
        color = MaterialTheme.colorScheme.successContainer.combineAlpha(NormalAlpha), // DisabledAlpha
        shape = MaterialTheme.shapes.large
    ) {
        Spacer(
            modifier = Modifier.padding(horizontal = 30.dp, vertical = 15.dp)
        )
    }
}
```

[NormalAlpha preview]: https://github.com/M3DZIK/android-utils/assets/87065584/79a73850-f290-445f-af43-ec112d5af727
[DisabledAlpha preview]: https://github.com/M3DZIK/android-utils/assets/87065584/18f0629a-6c50-4a5d-bd84-3e55c6fc709b

## isDark

The `MaterialTheme.colorScheme.isDark` returns `true` if the background color
is dark, `false` otherwise.

```kotlin
@Composable
private fun Example() {
    val color = if (MaterialTheme.colorScheme.isDark) Color.White else Color.Black
    
    // ..
}
```
