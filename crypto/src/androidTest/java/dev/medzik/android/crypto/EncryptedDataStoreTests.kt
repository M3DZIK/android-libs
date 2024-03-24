package dev.medzik.android.crypto

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import dev.medzik.android.crypto.EncryptedDataStore.deleteEncryptedKey
import dev.medzik.android.crypto.EncryptedDataStore.readEncryptedKey
import dev.medzik.android.crypto.EncryptedDataStore.writeEncryptedKey
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EncryptedDataStoreTests {
    enum class KeyAlias : KeyStoreAlias {
        TEST_DATASTORE_ENCRYPTED
    }

    private val testEncryptedKey = "test_encrypted_key"

    @Test
    fun testEncryptedDataStore() =
        runBlocking {
            val value = "Hello World!"

            val context = InstrumentationRegistry.getInstrumentation().context

            context.dataStore.writeEncryptedKey(KeyAlias.TEST_DATASTORE_ENCRYPTED, testEncryptedKey, value.toByteArray())
            Assert.assertEquals(value, String(context.dataStore.readEncryptedKey(KeyAlias.TEST_DATASTORE_ENCRYPTED, testEncryptedKey)!!))

            context.dataStore.deleteEncryptedKey(testEncryptedKey)
            Assert.assertEquals(null, context.dataStore.readEncryptedKey(KeyAlias.TEST_DATASTORE_ENCRYPTED, testEncryptedKey))

            KeyStore.deleteKey(KeyAlias.TEST_DATASTORE_ENCRYPTED.name)
        }
}
