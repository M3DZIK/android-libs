package dev.medzik.android.crypto

import android.content.Context
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import dev.medzik.android.crypto.DataStore.delete
import dev.medzik.android.crypto.DataStore.read
import dev.medzik.android.crypto.DataStore.write
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

val Context.dataStore by preferencesDataStore(name = "crypto_datastore_tests")

@RunWith(AndroidJUnit4::class)
class DataStoreTests {
    private val testKey = stringPreferencesKey("test_key")

    @Test
    fun testDataStore() =
        runBlocking {
            val value = "Hello World!"

            val context = InstrumentationRegistry.getInstrumentation().context

            context.dataStore.write(testKey, value)
            assertEquals(value, context.dataStore.read(testKey))

            context.dataStore.delete(testKey)
            assertEquals(null, context.dataStore.read(testKey))
        }
}
