# Encrypted DataStore

## EncryptedDataStore and Serializer

To create an encrypted datastore, create a data class that extends `EncryptedDataStore`.
Then create a serializer for this class.

Example:

```kotlin
enum class Key : KeyStoreAlias {
    EncryptedDataStore
}

@Serializable
data class SecretsStore(
    val value: String
) : EncryptedDataStore

object SecretsStoreSerializer : EncryptedDataStoreSerializer<SecretsStore> {
    override val keyStoreAlias = Key.EncryptedDataStore
    override val defaultValue = SecretsStore("")

    override fun encode(t: SecretsStore): String {
        return Json.encodeToString(t)
    }

    override fun decode(str: String): SecretsStore {
        return Json.decodeFromString(str)
    }
}
```

## Create a datastore

Create a datastore instance with this `SecretsStoreSerializer` serializer.

```kotlin
private val Context.secretsDataStore: DataStore<SecretsStore> by dataStore(
    fileName = "secretsStore.pb",
    serializer = SecretsStoreSerializer
)
```

## Read/Write Data

```kotlin
suspend fun example(context: Context) {
    // write data to datastore
    context.secretsDataStore..updateData {
        SecretsStore(
            value = "Hello World"
        )
    }

    // read data from datastore
    val secretsStore = context.dataStore.data.first()
    Assert.assertEquals("Hello World", secretsStore.value)
}
```
