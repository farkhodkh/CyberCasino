package ru.cybercasino.service_network.interceptors

import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 * The base [Interceptor] to set headers to the request.
 */
abstract class HeaderInterceptor : Interceptor {
    final override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        proceed(
            request()
                .newBuilder()
                .apply {
                    runBlocking {
                        setHeaders()
                    }
                }
                .build()
        )
    }

    /**
     * Sets the headers to the [Request.Builder].
     */
    abstract suspend fun Request.Builder.setHeaders()
}