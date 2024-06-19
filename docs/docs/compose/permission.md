# Permission

Checks if the application has granted permission for the requested permission.
* If the permission is already granted, it calls the [onGranted] block.
* If the permission is not granted, it calls the [onDenied] block with a requester function
  that launches the permission request.

```kotlin
@Compose
fun RequestCameraPermission() {
    Permission(
        permission = Manifest.permission.CAMERA,
        onDenied = { requestPermission: @Composable () -> Unit ->
            requestPermission()
            Text(text = "Camera permission is required")
        },
        onGranted = {
            Text(text = "Camera permission is granted")
        }
    )
}
```
