package dev.medzik.android.crypto

import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.medzik.libcrypto.Hex
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class KeyStoreTests {
    @Test
    fun testEncryptDecrypt() {
        val alias = "test"
        val clearText = "Hello World!"

        // encrypt
        val cipherEnc = KeyStore.initForEncryption(alias, false)
        val encryptedData = KeyStore.encrypt(cipherEnc, clearText.toByteArray())

        // decrypt
        val cipherDec = KeyStore.initForDecryption(Hex.decode(encryptedData.initializationVector), alias, false)
        val decryptedBytes = KeyStore.decrypt(cipherDec, encryptedData.cipherText)

        assertEquals(clearText, String(decryptedBytes))
    }
}
