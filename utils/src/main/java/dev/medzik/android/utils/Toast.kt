package dev.medzik.android.utils

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

/**
 * Shows a toast with the given message.
 *
 * @param text The message to be shown.
 */
fun Context.showToast(text: String) =
    runOnUiThread {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }

/**
 * Shows a toast with the given message.
 *
 * @param resId The resource id of the string resource to be shown.
 */
fun Context.showToast(
    @StringRes resId: Int
) = runOnUiThread {
    Toast.makeText(this, resId, Toast.LENGTH_LONG).show()
}
