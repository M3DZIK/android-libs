package dev.medzik.android.crypto

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

/**
 * Utility class for storing data in the DataStore.
 */
object DataStore {
    /**
     * Reads the value for the given key from the DataStore.
     *
     * @param preferenceKey preference key in the DataStore
     * @return value stored under the specified key, or null if not found.
     */
    suspend inline fun <reified T> DataStore<Preferences>.read(preferenceKey: Preferences.Key<T>): T? {
        return data.map { it[preferenceKey] }.first()
    }

    /**
     * Writes the value for the given key to the DataStore.
     *
     * @param preferenceKey preference key in the DataStore
     * @param value key value
     */
    suspend inline fun <reified T> DataStore<Preferences>.write(
        preferenceKey: Preferences.Key<T>,
        value: T
    ) {
        edit { it[preferenceKey] = value }
    }

    /**
     * Deletes the value for the given key from the DataStore.
     *
     * @param preferenceKey preference key in the DataStore
     */
    suspend inline fun <reified T> DataStore<Preferences>.delete(preferenceKey: Preferences.Key<T>) {
        edit { it.remove(preferenceKey) }
    }
}
