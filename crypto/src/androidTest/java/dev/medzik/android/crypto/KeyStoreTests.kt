package dev.medzik.android.crypto

import androidx.test.ext.junit.runners.AndroidJUnit4
import okio.ByteString.Companion.decodeHex
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class KeyStoreTests {
    object TestKeyAlias : KeyStoreAlias {
        override val name = "test_key"
    }

    @OptIn(ExperimentalStdlibApi::class)
    @Test
    fun testEncryptDecrypt() {
        val clearText = "Hello World!"

        // encrypt
        val cipherEnc = KeyStore.initForEncryption(TestKeyAlias, false)
        val encryptedData = KeyStore.encrypt(cipherEnc, clearText.toByteArray())

        // decrypt
        val cipherDec = KeyStore.initForDecryption(
            TestKeyAlias,
            encryptedData.initializationVector.hexToByteArray(),
            false
        )
        val decryptedBytes = KeyStore.decrypt(cipherDec, encryptedData.cipherText)

        assertEquals(clearText, String(decryptedBytes))

        KeyStore.deleteKey(TestKeyAlias)
    }
}
