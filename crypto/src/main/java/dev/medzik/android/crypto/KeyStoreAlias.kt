package dev.medzik.android.crypto

/**
 * Interface representing a keystore alias for keystore operations.
 *
 * This interface should be implemented by object or enum that represents a keystore alias.
 */
interface KeyStoreAlias {
    /**
     * KeyStore alias name.
     */
    val name: String
}
