package ru.cybercasino.service_network.retrofit

import okhttp3.Authenticator
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit

class RetrofitProviderImpl : RetrofitProvider {
    private val V1 = "v1"
    override val defaultBaseUrl = "http://kazino-back.dev2.itdept.cloud/api/$V1/"
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        setLevel(
            HttpLoggingInterceptor.Level.BODY
        )
    }

    override fun provide(
        url: String,
        authenticator: Authenticator?,
        interceptors: List<Interceptor>,
        converterFactory: Converter.Factory
    ): Retrofit = Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient(authenticator, interceptors))
        .addConverterFactory(converterFactory)
        .build()

    private fun okHttpClient(
        authenticator: Authenticator?,
        interceptors: List<Interceptor>
    ) = OkHttpClient().newBuilder()
        .apply {
            addInterceptor(loggingInterceptor)
            interceptors.toSet().forEach {
                addInterceptor(it)
            }
        }
        .apply {
            if (authenticator != null) {
                authenticator(authenticator)
            }
        }
        .build()
}