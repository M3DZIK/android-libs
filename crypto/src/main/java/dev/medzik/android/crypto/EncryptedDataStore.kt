package dev.medzik.android.crypto

import androidx.datastore.core.Serializer
import okio.ByteString.Companion.decodeHex
import java.io.*
import java.nio.charset.StandardCharsets

/**
 * Interface representing an encrypted data store.
 *
 * This interface should be implemented by any class that needs to be stored encoded in a data store.
 * It serves as a contract for the classes that will be serialized and deserialized using the
 * [EncryptedDataStoreSerializer].
 *
 * @see EncryptedDataStoreSerializer
 */
interface EncryptedDataStore

/**
 * Interface representing a serializer for encrypted data store.
 *
 * This serializer serializes and deserializes [EncryptedDataStore] objects.
 *
 * @See EncryptedDataStore
 */
interface EncryptedDataStoreSerializer<T: EncryptedDataStore> : Serializer<T> {
    /**
     * The alias of the key in the KeyStore to be used for encryption and decryption.
     */
    val keyStoreAlias: KeyStoreAlias

    /**
     * Encodes the given [EncryptedDataStore] instance into a string.
     *
     * @param t the instance to be encoded
     * @return The encoded string.
     */
    fun encode(t: T): String

    /**
     * Decodes the given string into an [EncryptedDataStore] instance.
     *
     * @param str the string to be decoded
     * @return The decoded [EncryptedDataStore] instance.
     */
    fun decode(str: String): T

    /**
     * Reads and decrypts the [EncryptedDataStore] from data store.
     */
    @OptIn(ExperimentalStdlibApi::class)
    override suspend fun readFrom(input: InputStream): T {
        // read string from InputStream
        val cipherTextWithIVBuilder = StringBuilder()
        BufferedReader(InputStreamReader(input, StandardCharsets.UTF_8)).use { reader ->
            var c: Int
            while ((reader.read().also { c = it }) != -1) {
                cipherTextWithIVBuilder.append(c.toChar())
            }
        }

        // initialization vector length in hex string
        val ivLength = 12 * 2

        // extract IV and Cipher Text from hex string
        val iv = cipherTextWithIVBuilder.substring(0, ivLength)
        val cipherText = cipherTextWithIVBuilder.substring(ivLength)

        // decrypt cipher text
        val cipher = KeyStore.initForDecryption(keyStoreAlias, iv.hexToByteArray(), false)
        val decrypted = KeyStore.decrypt(cipher, cipherText)

        return decode(String(decrypted))
    }

    /**
     * Encrypts and writes the [EncryptedDataStore] to data store.
     */
    override suspend fun writeTo(
        t: T,
        output: OutputStream
    ) {
        val encoded = encode(t)

        val cipher = KeyStore.initForEncryption(keyStoreAlias, false)
        val cipherData = KeyStore.encrypt(cipher, encoded.toByteArray())

        val cipherText = cipherData.initializationVector + cipherData.cipherText

        // write string to OutputStream
        OutputStreamWriter(output, StandardCharsets.UTF_8).use { writer ->
            writer.write(cipherText)
        }
    }
}
