# KeyStore

[Javadoc documentation][javadoc]

[javadoc]: https://www.javadoc.io/doc/dev.medzik.android/crypto/latest/crypto/dev.medzik.android.crypto/-key-store/index.html

## Key alias

To create a keystore key alias you need to create a class that extends `KeyStoreAlias`.

```kotlin
enum class Key : KeyStoreAlias {
    TestKey
}
```

or

```kotlin
object TestKeyAlias : KeyStoreAlias {
    override val name = "test_key"
}
```

## Encryption and Decryption

Example code of encryption and decryption text using Android KeyStore:

```kotlin
import dev.medzik.android.crypto.KeyStore
import dev.medzik.libcrypto.Hex

val alias = Key.TestKey // or TestKeyAlias
val inputText = "Hello World!"

// initialize cipher for encryption
val initializedCipherForEncryption = KeyStore.initForEncryption(alias, deviceAuthentication = false)
// encrypt the input
val encryptedText = KeyStore.encrypt(initializedCipherForEncryption, clearText.toByteArray())

// initialize cipher for decryption
val initializedCipherForDecryption = KeyStore.initForDecryption(
    alias,
    Hex.decode(encryptedData.initializationVector),
    deviceAuthentication = false
)
// decrypt the cipher text
val decryptedBytes = KeyStore.decrypt(initializedCipherForDecryption, encryptedData.cipherText)
```
