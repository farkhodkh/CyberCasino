package ru.cybercasino.core.network.security

/**
 * The result of the encryption.
 * Holds the encrypted data and an initialization vector.
 */
data class EncryptionResult(
    /**
     * Base64 of the encrypted value.
     */
    val encrypted: String,
    /**
     * Base64 of the initialization vector.
     */
    val iv: String
) {
    companion object {

        /**
         * Base64 encoded string does not include the colon symbol.
         */
        private const val SEPARATOR = ":"

        /**
         * Serializes the given [EncryptionResult] into string.
         *
         * @param result The result to serialize into string.
         */
        fun serialize(result: EncryptionResult): String =
            "${result.encrypted}$SEPARATOR${result.iv}"

        /**
         * Deserializes the given string into [EncryptionResult].
         *
         * @param value The previously serialized [EncryptionResult].
         */
        fun deserialize(value: String): EncryptionResult =
            value.split(SEPARATOR).let { EncryptionResult(it[0], it[1]) }
    }
}
