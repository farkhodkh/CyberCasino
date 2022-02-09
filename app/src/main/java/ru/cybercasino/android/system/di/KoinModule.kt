package ru.cybercasino.android.system.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import org.koin.dsl.module
import ru.cybercasino.feature.auth.viewmodel.LoginScreenViewModel
import ru.cybercasino.network.RetrofitProvider
import ru.cybercasino.network.RetrofitProvider.Type.Basic
import ru.cybercasino.network.RetrofitProvider.Type.Authenticated

/**
 * The application module.
 */
val appModule = module {
//    factory<NavigationIntentProvider> { NavigationIntentProviderImpl() }
//
    single(named(Basic)) { createBasicRetrofit() }
    single(named(Authenticated)) { createAuthenticatedRetrofit() }

    viewModel { LoginScreenViewModel() }
}

private fun Scope.createBasicRetrofit() =
    get<RetrofitProvider>().provide(
        interceptors = listOf(
            get<ApiHeadersInterceptor>(),
        )
    )

private fun Scope.createAuthenticatedRetrofit() =
    get<RetrofitProvider>().provide(
        authenticator = get<ApiAuthenticator>(),
        interceptors = listOf(
            get<ApiHeadersInterceptor>(),
            get<AuthInterceptor>(),
            get<ClientUuidInterceptor>()
        )
    )