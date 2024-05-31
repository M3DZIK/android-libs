package dev.medzik.android.crypto

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EncryptedDataStoreTests {
    @Serializable
    data class SecretsStore(
        val value: String
    ) : EncryptedDataStore

    object SecretsStoreSerializer : EncryptedDataStoreSerializer<SecretsStore> {
        override val keyStoreAlias = DataStoreKeyAlias
        override val defaultValue = SecretsStore("")

        override fun encode(t: SecretsStore): String {
            return Json.encodeToString(t)
        }

        override fun decode(str: String): SecretsStore {
            return Json.decodeFromString(str)
        }
    }

    private val Context.dataStore: DataStore<SecretsStore> by dataStore(
        fileName = "secretsStore.pb",
        serializer = SecretsStoreSerializer
    )

    object DataStoreKeyAlias : KeyStoreAlias {
        override val name = "encrypted_datastore"
    }

    @Test
    fun testEncryptedDataStore() = runBlocking {
        val value = "Hello World!"

        val context = InstrumentationRegistry.getInstrumentation().context

        context.dataStore.updateData { SecretsStore(value) }
        Assert.assertEquals(value, context.dataStore.data.first().value)
    }
}
