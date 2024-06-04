package dev.medzik.android.components

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat

/**
 * A composable function that checks if the app has the requested permission.
 * If the permission is already granted, it calls the [onGranted] block.
 * If the permission is not granted, it calls the [onDenied] block with a requester function
 * that launches the permission request.
 *
 * @param permission the name of the permission
 * @param onDenied composable function displayed when the permission is denied
 * @param onGranted an optional composable function displayed when the permission is granted
 */
@Composable
fun Permission(
    permission: String,
    onDenied: @Composable (requestPermission: @Composable () -> Unit) -> Unit,
    onGranted: @Composable () -> Unit = {}
) {
    // check the initial state of permission, it may be already granted
    var grantState by rememberMutableBoolean(
        ContextCompat.checkSelfPermission(
            LocalContext.current,
            permission
        ) == PackageManager.PERMISSION_GRANTED
    )

    if (grantState) {
        onGranted()
    } else {
        val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission()) {
            grantState = it
        }

        onDenied { SideEffect { launcher.launch(permission) } }
    }
}

@Preview
@Composable
private fun PermissionPreview() {
    Permission(
        permission = Manifest.permission.CAMERA,
        onDenied = { requestPermission ->
            requestPermission()
            Text(text = "Camera permission is required")
        },
        onGranted = {
            Text(text = "Camera permission is granted")
        }
    )
}
