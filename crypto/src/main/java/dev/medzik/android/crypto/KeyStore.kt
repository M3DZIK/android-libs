package dev.medzik.android.crypto

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import dev.medzik.libcrypto.Hex
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec

/** Interface representing an alias for keystore operations. */
interface KeyStoreAlias {
    val name: String
}

/** Utility class for encrypting and decrypting data using the Android KeyStore. */
object KeyStore {
    private const val AES_MODE = "AES/GCM/NoPadding"

    /**
     * Initializes a new Cipher for encryption.
     *
     * @param alias The secret key alias in Android KeyStore.
     * @param deviceAuthentication Whether user authentication is required to access the secret key.
     * @return An initialized Cipher for encryption.
     */
    fun initForEncryption(
        alias: KeyStoreAlias,
        deviceAuthentication: Boolean
    ): Cipher {
        val cipher = Cipher.getInstance(AES_MODE)
        cipher.init(Cipher.ENCRYPT_MODE, getOrGenerateSecretKey(alias.name, deviceAuthentication))
        return cipher
    }

    /**
     * Initializes a new Cipher for decryption.
     *
     * @param initializationVector The initialization vector used for Cipher decryption.
     * @param alias The secret key alias in Android KeyStore.
     * @param deviceAuthentication Whether user authentication is required to access the secret key.
     * @return An initialized Cipher for decryption.
     */
    fun initForDecryption(
        initializationVector: ByteArray,
        alias: KeyStoreAlias,
        deviceAuthentication: Boolean
    ): Cipher {
        val cipher = Cipher.getInstance(AES_MODE)
        cipher.init(
            Cipher.DECRYPT_MODE,
            getOrGenerateSecretKey(alias.name, deviceAuthentication),
            GCMParameterSpec(128, initializationVector)
        )
        return cipher
    }

    /**
     * Encrypts the given data using the specified Cipher.
     *
     * @param cipher The initialized cipher.
     * @param clearBytes The clear bytes to encrypt.
     * @return A CipherText object containing the encrypted data and initialization vector.
     */
    fun encrypt(
        cipher: Cipher,
        clearBytes: ByteArray
    ): CipherText {
        return CipherText(
            cipherText = Hex.encode(cipher.doFinal(clearBytes)),
            initializationVector = Hex.encode(cipher.iv)
        )
    }

    /**
     * Decrypts the given cipher text using the specified cipher.
     *
     * @param cipher The initialized cipher.
     * @param cipherText The encrypted data to decrypt
     * @return The decrypted data as a byte array.
     */
    @Throws(Exception::class)
    fun decrypt(
        cipher: Cipher,
        cipherText: String
    ): ByteArray {
        return cipher.doFinal(Hex.decode(cipherText))
    }

    /**
     * Deletes the secret key with the given alias from the Android KeyStore.
     *
     * @param alias The secret key alias in Android KeyStore.
     */
    fun deleteKey(alias: KeyStoreAlias) = deleteKey(alias.name)

    /**
     * Deletes the secret key with the given alias from the Android KeyStore.
     *
     * @param alias The secret key alias in Android KeyStore.
     */
    fun deleteKey(alias: String) = getKeyStore().deleteEntry(alias)

    /** Gets the secret key if it exists, otherwise generates a new secret key. */
    private fun getOrGenerateSecretKey(
        alias: String,
        deviceAuthentication: Boolean
    ): SecretKey {
        return if (secretKeyExists(alias)) {
            getKeyStore().getKey(alias, null) as SecretKey
        } else {
            generateSecretKey(alias, deviceAuthentication)
        }
    }

    /** Checks if the given alias of a secret key exists. */
    private fun secretKeyExists(alias: String): Boolean {
        return getKeyStore().containsAlias(alias)
    }

    /** Generates a new secret key */
    private fun generateSecretKey(
        alias: String,
        requireAuthentication: Boolean
    ): SecretKey {
        val keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore")

        val keyGenParameterSpec =
            KeyGenParameterSpec.Builder(
                alias,
                KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
            )
                .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
                .setUserAuthenticationRequired(requireAuthentication)
                .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                .build()

        keyGenerator.init(keyGenParameterSpec)
        return keyGenerator.generateKey()
    }

    /** Returns the Android KeyStore */
    private fun getKeyStore(): KeyStore {
        return KeyStore.getInstance("AndroidKeyStore").apply { load(null) }
    }

    data class CipherText(
        val cipherText: String,
        val initializationVector: String
    )
}
