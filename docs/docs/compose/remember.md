# Remembers

## rememberMutable

Remembers mutable state of the value. It is basically `remember { mutableStateOf(initialValue) }`.

```kotlin
@Compose
fun Screen() {
    var value by rememberMutable(false)

    LaunchedEffect(Unit) {
        loaded = true
    }
    
    if (loaded) {
        Text("Loaded")
    }
}
```

## rememberSaveableMutable

The same as [rememberMutable](#remembermutable) but the stored value
will survive the activity or process recreation.
For example when the screen is rotated in the Android application.
