package dev.medzik.android.utils

import android.app.Activity
import android.content.Intent
import android.net.Uri

/**
 * Opens the default email application with the given email address, subject, and body.
 *
 * @param email The email address.
 * @param subject The subject of the email.
 * @param body The body of the email.
 */
fun Activity.openEmailApplication(
    email: String,
    subject: String? = null,
    body: String? = null
) {
    val intent =
        Intent(
            Intent.ACTION_SENDTO,
            Uri.parse("mailto:$email")
        )

    if (!subject.isNullOrBlank()) {
        intent.putExtra(Intent.EXTRA_SUBJECT, subject)
    }
    if (!subject.isNullOrBlank()) {
        intent.putExtra(Intent.EXTRA_TEXT, body)
    }

    startActivity(Intent.createChooser(intent, "Email"))
}
