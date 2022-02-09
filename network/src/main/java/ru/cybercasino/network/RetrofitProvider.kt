package ru.cybercasino.network

import com.squareup.moshi.Moshi
import java.net.Authenticator
import okhttp3.Interceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * The component which provides [Retrofit] instance.
 */
interface RetrofitProvider {
    /**
     * The default base URL.
     */
    val defaultBaseUrl: String

    /**
     * Creates and provides the [Retrofit] instance.
     *
     * @param url The base URL.
     * @param authenticator The [Authenticator] instance.
     * @param interceptors The interceptors to use within OkHttp client.
     * @param converterFactory The converter factory for serialization and deserialization of objects.
     */
    fun provide(
        url: String = defaultBaseUrl,
        authenticator: Authenticator? = null,
        interceptors: List<Interceptor> = emptyList(),
        converterFactory: Converter.Factory = MoshiConverterFactory.create(Moshi.Builder().build())
    ): Retrofit

    /**
     * The instance types.
     */
    enum class Type {
        /**
         * The basic one adds only minimal info interceptors.
         */
        Basic,

        /**
         * The type which has authenticator and authentication related interceptors (e.g. tokens).
         */
        Authenticated
    }
}