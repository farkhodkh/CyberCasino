package ru.cybercasino.android.system.di

import kotlinx.coroutines.GlobalScope
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import org.koin.dsl.module
import retrofit2.Retrofit
import ru.cybercasino.feature.auth.LoginController
import ru.cybercasino.feature.auth.viewmodel.LoginScreenViewModel
import ru.cybercasino.network.RetrofitProvider
import ru.cybercasino.network.RetrofitProvider.Type.Basic
import ru.cybercasino.network.RetrofitProvider.Type.Authenticated
import ru.cybercasino.network.data.ApiHeadersInterceptor
import ru.cybercasino.network.model.Environment
import ru.cybercasino.network.model.HttpUtils
import ru.cybercasino.network.providers.HttpClientProvider
import ru.cybercasino.network.providers.HttpClientProviderImpl

/**
 * The application module.
 */
val appModule = module {
//    factory<NavigationIntentProvider> { NavigationIntentProviderImpl() }
//
    single { LoginController(GlobalScope, get()) }

    single { ApiHeadersInterceptor() }
    single { HttpClientProviderImpl(get(), get(), HttpUtils.makeRestApiTimeoutsConfig()) }
    single(named(Basic)) { createBasicRetrofit() }
//    single(named(Authenticated)) { createAuthenticatedRetrofit() }

    viewModel { LoginScreenViewModel(get()) }
}

private fun Scope.createBasicRetrofit(): Retrofit =
    get<RetrofitProvider>().provide(
        url = Environment.Dev.baseUrl,
        interceptors = listOf(
            get<ApiHeadersInterceptor>(),
        )
    )

//private fun Scope.createAuthenticatedRetrofit() =
//    get<RetrofitProvider>().provide(
//        authenticator = get<ApiAuthenticator>(),
//        interceptors = listOf(
//            get<ApiHeadersInterceptor>(),
//            get<AuthInterceptor>(),
//            get<ClientUuidInterceptor>()
//        )
//    )