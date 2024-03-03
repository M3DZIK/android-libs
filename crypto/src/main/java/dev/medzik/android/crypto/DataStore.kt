package dev.medzik.android.crypto

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import dev.medzik.libcrypto.Hex
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

/** Utility class for storing data using Android's DataStore. */
object DataStore {
    /**
     * Reads a value associated with the specified key from the DataStore.
     *
     * @param preferenceKey The key to retrieve the value for.
     * @return The value stored under the specified key, or null if not found.
     */
    suspend inline fun <reified T> DataStore<Preferences>.read(preferenceKey: Preferences.Key<T>): T? {
        return data.map { it[preferenceKey] }.first()
    }

    /**
     * Writes a value to the DataStore under the specified key.
     *
     * @param preferenceKey The key to store the value under.
     * @param value The value to store.
     */
    suspend inline fun <reified T> DataStore<Preferences>.write(
        preferenceKey: Preferences.Key<T>,
        value: T
    ) {
        edit { it[preferenceKey] = value }
    }

    /**
     * Deletes the value associated with the specified key from the DataStore.
     *
     * @param preferenceKey The key of the value to be deleted.
     */
    suspend inline fun <reified T> DataStore<Preferences>.delete(preferenceKey: Preferences.Key<T>) {
        edit { it.remove(preferenceKey) }
    }

    /**
     * Reads a value associated with the specified encrypted key from the DataStore.
     *
     * @param keyStoreAlias The alias of the secret key in the Android KeyStore used for decryption.
     * @param preferenceKey The key to retrieve the encrypted value for.
     * @return The decrypted value stored under the specified encrypted key, or null if not found.
     */
    suspend fun DataStore<Preferences>.readEncrypted(
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
        val cipher = KeyStore.initForDecryption(Hex.decode(iv), keyStoreAlias, false)
        return KeyStore.decrypt(cipher, cipherText)
    }

    /**
     * Writes a encrypted value to the DataStore under the specified key.
     *
     * @param keyStoreAlias The alias of the secret key in the Android KeyStore used for encryption.
     * @param preferenceKey The key to store the encrypted value under.
     * @param value The value to store.
     */
    suspend fun DataStore<Preferences>.writeEncrypted(
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
     * Deletes the encrypted value associated with the specified key from the DataStore.
     *
     * @param preferenceKey The key of the encrypted value to be deleted.
     */
    suspend fun DataStore<Preferences>.deleteEncrypted(preferenceKey: String) {
        val cipherTextStore = stringPreferencesKey("$preferenceKey/encrypted")
        delete(cipherTextStore)
    }
}
