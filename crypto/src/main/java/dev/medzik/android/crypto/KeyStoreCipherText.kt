package dev.medzik.android.crypto

/**
 * Represents a cipher text.
 *
 * @param cipherText encrypted data
 * @param initializationVector initialization vector of the cipher text
 */
data class KeyStoreCipherText(
    val cipherText: String,
    val initializationVector: String
)
