package dev.medzik.android.crypto

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import dev.medzik.android.crypto.DataStore.delete
import dev.medzik.android.crypto.DataStore.read
import dev.medzik.android.crypto.DataStore.write
import dev.medzik.libcrypto.Hex

/**
 * Utility class for storing encrypted data in the DataStore.
 */
object EncryptedDataStore {
    /**
     * Reads and decrypts the encrypted value for the given key from the DataStore.
     *
     * @param keyStoreAlias alias from the KeyStore
     * @param preferenceKey preference key in the DataStore
     * @return decrypted value stored under the specified encrypted key, or null if not found
     */
    suspend fun DataStore<Preferences>.readEncryptedKey(
        keyStoreAlias: KeyStoreAlias,
        preferenceKey: String
    ): ByteArray? {
        val cipherTextStore = stringPreferencesKey("$preferenceKey/encrypted")

        // read cipher text from datastore
        val cipherTextWithIV = read(cipherTextStore) ?: return null

        // initialization vector length in hex string
        val ivLength = 12 * 2

        // extract IV and Cipher Text from hex string
        val iv = cipherTextWithIV.substring(0, ivLength)
        val cipherText = cipherTextWithIV.substring(ivLength)

        // decrypt cipher text
        val cipher = KeyStore.initForDecryption(keyStoreAlias, Hex.decode(iv), false)
        return KeyStore.decrypt(cipher, cipherText)
    }

    /**
     * Encrypts and writes the encrypted value to the DataStore.
     *
     * @param keyStoreAlias alias from the KeyStore
     * @param preferenceKey preference key in the DataStore
     * @param value The value to store.
     */
    suspend fun DataStore<Preferences>.writeEncryptedKey(
        keyStoreAlias: KeyStoreAlias,
        preferenceKey: String,
        value: ByteArray
    ) {
        val cipherTextStore = stringPreferencesKey("$preferenceKey/encrypted")

        // encrypt value
        val cipher = KeyStore.initForEncryption(keyStoreAlias, false)
        val cipherData = KeyStore.encrypt(cipher, value)

        // write encrypted value to datastore
        val cipherText = cipherData.initializationVector + cipherData.cipherText
        write(cipherTextStore, cipherText)
    }

    /**
     * Deletes the encrypted value for the given key from the DataStore.
     *
     * @param preferenceKey preference key in the DataStore
     */
    suspend fun DataStore<Preferences>.deleteEncryptedKey(preferenceKey: String) {
        val cipherTextStore = stringPreferencesKey("$preferenceKey/encrypted")
        delete(cipherTextStore)
    }
}
