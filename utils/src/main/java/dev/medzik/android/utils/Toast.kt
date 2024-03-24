package dev.medzik.android.utils

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

/**
 * Shows toast with the given message.
 *
 * @param text toast message
 */
fun Context.showToast(text: String) =
    runOnUiThread {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }

/**
 * Shows toast with the given message.
 *
 * @param resId resource id of the toast message
 */
fun Context.showToast(
    @StringRes resId: Int
) = runOnUiThread {
    Toast.makeText(this, resId, Toast.LENGTH_LONG).show()
}
