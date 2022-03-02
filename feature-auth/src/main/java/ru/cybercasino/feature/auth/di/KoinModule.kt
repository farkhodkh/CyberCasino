package ru.cybercasino.feature.auth.di

import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import org.koin.dsl.module
import retrofit2.Retrofit
import ru.cybercasino.core.network.security.SecurityHelper
import ru.cybercasino.core.network.security.SecurityHelperImpl
import ru.cybercasino.feature.auth.LoginController
import ru.cybercasino.feature.auth.api.AuthenticationStorageRepository
import ru.cybercasino.feature.auth.api.AuthenticationStorageRepositoryImpl
import ru.cybercasino.feature.auth.viewmodel.AuthorizationViewModel
import ru.cybercasino.feature.auth_api.AuthenticationApi
import ru.cybercasino.service_network.interceptors.ApiHeadersInterceptor
import ru.cybercasino.service_network.retrofit.RetrofitProvider
import ru.cybercasino.service_network.retrofit.RetrofitProviderImpl

/**
 * Authentification module
 */
val appModuleAuth = module {
    factory { ApiHeadersInterceptor() }
    single<RetrofitProvider> { RetrofitProviderImpl() }
    single<SecurityHelper> { SecurityHelperImpl(Dispatchers.Default) }
    single<AuthenticationStorageRepository> { AuthenticationStorageRepositoryImpl(androidContext(), get()) }
    single(named(RetrofitProvider.Type.Basic)) { createBasicRetrofit() }
    single {
        LoginController(
            get<Retrofit>(named(RetrofitProvider.Type.Basic)).create(
                AuthenticationApi::class.java
            )
        )
    }
    viewModel { AuthorizationViewModel(get(), get()) }
}

private fun Scope.createBasicRetrofit() =
    get<RetrofitProvider>().provide(
        interceptors = listOf(
            get<ApiHeadersInterceptor>(),
        )
    )