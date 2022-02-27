package ru.cybercasino.core.network.security

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Base64
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext

class SecurityHelperImpl(
    private val dispatcher: CoroutineDispatcher
) : SecurityHelper {

    private val mutex = Mutex()
    private val cipher by lazy { Cipher.getInstance(TRANSFORMATION) }
    private val keyStore by lazy { KeyStore.getInstance(PROVIDER).apply { load(null) } }

    private val keyGenerator by lazy { KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, PROVIDER) }

    override suspend fun encrypt(value: String): EncryptionResult =
        mutex.withLock {
            withContext(dispatcher) {
                cipher.init(Cipher.ENCRYPT_MODE, generateSecretKey())
                EncryptionResult(
                    cipher.doFinal(value.toByteArray()).encodeBase64(),
                    cipher.iv.encodeBase64()
                )
            }
        }

    override suspend fun decrypt(data: EncryptionResult): String =
        mutex.withLock {
            withContext(dispatcher) {
                val spec = GCMParameterSpec(TAG_LENGTH, data.iv.decodeBase64())
                cipher.init(Cipher.DECRYPT_MODE, generateSecretKey(), spec)
                val value = cipher.doFinal(data.encrypted.decodeBase64())
                String(value)
            }
        }

    private fun generateSecretKey(): SecretKey =
        if (keyStore.containsAlias(SECRET_KEY_ALIAS)) {
            (keyStore.getEntry(SECRET_KEY_ALIAS, null) as KeyStore.SecretKeyEntry).secretKey
        } else {
            val spec = KeyGenParameterSpec
                .Builder(SECRET_KEY_ALIAS, KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT)
                .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
                .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                .build()
            keyGenerator.init(spec)
            keyGenerator.generateKey()
        }

}

/**
 * Transforms [ByteArray] value to base64 encoded value.
 */
private fun ByteArray.encodeBase64(): String =
    Base64.encodeToString(this, Base64.NO_WRAP)

/**
 * Transforms base64 encoded value to [ByteArray] value.
 */
private fun String.decodeBase64(): ByteArray =
    Base64.decode(this, Base64.NO_WRAP)

private const val PROVIDER = "AndroidKeyStore"
private const val TRANSFORMATION = "AES/GCM/NoPadding"
private const val SECRET_KEY_ALIAS = "DATA"
private const val TAG_LENGTH = 128