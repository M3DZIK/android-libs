package dev.medzik.android.crypto

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.IllegalBlockSizeException
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec

@OptIn(ExperimentalStdlibApi::class)
object KeyStore {
    /**
     * Initializes a new Cipher for encryption.
     *
     * @param alias keystore alias
     * @param deviceAuthentication whether user authentication is required to access the secret key
     * @return initialized Cipher for encryption.
     */
    fun initForEncryption(
        alias: KeyStoreAlias,
        deviceAuthentication: Boolean
    ): Cipher {
        val cipher = createEmptyCipher()
        val secretKey = getSecretKey(alias.name, deviceAuthentication)
        cipher.init(Cipher.ENCRYPT_MODE, secretKey)
        return cipher
    }

    /**
     * Initializes a new Cipher for decryption.
     *
     * @param alias keystore alias
     * @param initializationVector the cipher initialization vector
     * @param deviceAuthentication whether user authentication is required to access the secret key
     * @return initialized Cipher for decryption.
     */
    fun initForDecryption(
        alias: KeyStoreAlias,
        initializationVector: ByteArray,
        deviceAuthentication: Boolean
    ): Cipher {
        val cipher = createEmptyCipher()
        val secretKey = getSecretKey(alias.name, deviceAuthentication)
        cipher.init(
            Cipher.DECRYPT_MODE,
            secretKey,
            GCMParameterSpec(128, initializationVector)
        )
        return cipher
    }

    /**
     * Encrypts the given data.
     *
     * @param cipher initialized cipher
     * @param clearBytes the data to be encrypted
     * @return [KeyStoreCipherText] object
     */
    fun encrypt(
        cipher: Cipher,
        clearBytes: ByteArray
    ) = KeyStoreCipherText(
        cipherText = cipher.doFinal(clearBytes).toHexString(),
        initializationVector = cipher.iv.toHexString()
    )

    /**
     * Decrypts the given cipher text.
     *
     * @param cipher initialized cipher
     * @param cipherText encrypted data
     * @return decrypted data as a byte array
     */
    @Throws(IllegalBlockSizeException::class)
    fun decrypt(
        cipher: Cipher,
        cipherText: String
    ): ByteArray = cipher.doFinal(cipherText.hexToByteArray())

    /**
     * Deletes the secret key with the given [alias] from the Android KeyStore.
     */
    fun deleteKey(alias: KeyStoreAlias) = getKeyStore().deleteEntry(alias.name)

    private fun createEmptyCipher(): Cipher = Cipher.getInstance(
        KeyProperties.KEY_ALGORITHM_AES + "/" +
        KeyProperties.BLOCK_MODE_GCM + "/" +
        KeyProperties.ENCRYPTION_PADDING_NONE,
    )

    private fun getSecretKey(
        alias: String,
        deviceAuthentication: Boolean
    ): SecretKey {
        val keyStore = getKeyStore()

        // if the key exists, return it
        val key = keyStore.getKey(alias, null)
        if (key != null) {
            return key as SecretKey
        }

        val keyPurpose = KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
        val keySpec = KeyGenParameterSpec.Builder(alias, keyPurpose)
            .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
            .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
            .setUserAuthenticationRequired(deviceAuthentication)
            .build()

        val keyGenerator = KeyGenerator
            .getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore")
        keyGenerator.init(keySpec)
        return keyGenerator.generateKey()
    }

    private fun getKeyStore(): KeyStore {
        val keyStore = KeyStore.getInstance("AndroidKeyStore")
        // before use keystore, it must be loaded
        keyStore.load(null)
        return keyStore
    }
}
