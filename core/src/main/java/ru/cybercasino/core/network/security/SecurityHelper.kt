package ru.cybercasino.core.network.security

/**
 * The helper which provides encryption / decryption mechanism.
 */
interface SecurityHelper {
    /**
     * Encrypt the given value.
     *
     * @param value Value to encrypt.
     * @return The encryption result with base64 encoded text and base64 encoded IV.
     */
    suspend fun encrypt(value: String): EncryptionResult

    /**
     * Decrypt the given data.
     *
     * @param data The encryption result with base64 encoded text and base64 encoded IV.
     * @return The original value.
     */
    suspend fun decrypt(data: EncryptionResult): String
}