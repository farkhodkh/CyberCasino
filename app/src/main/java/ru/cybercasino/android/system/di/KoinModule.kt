package ru.cybercasino.android.system.di

import kotlinx.coroutines.CoroutineScope
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
import ru.cybercasino.feature.auth.viewmodel.LoginScreenViewModel
import ru.cybercasino.feature.auth_api.AuthenticationApi
import ru.cybercasino.service_network.interceptors.ApiHeadersInterceptor
import ru.cybercasino.service_network.retrofit.RetrofitProvider
import ru.cybercasino.service_network.retrofit.RetrofitProviderImpl

/**
 * The application module.
 */
val appModule = module {
//    single<SecurityHelper> { SecurityHelperImpl(Dispatchers.Default) }
//
//    single<AuthenticationStorageRepository> {
//        AuthenticationStorageRepositoryImpl(androidContext(), get())
//    }
//    factory { ApiHeadersInterceptor() }
//
//    single<RetrofitProvider> { RetrofitProviderImpl() }
//
//    single {
//        LoginController(
//            get<Retrofit>(named(RetrofitProvider.Type.Basic)).create(
//                AuthenticationApi::class.java
//            )
//        )
//    }
//
//    single(named(RetrofitProvider.Type.Basic)) { createBasicRetrofit() }
//
//    viewModel { LoginScreenViewModel(get(), get()) }
//    single(named(RetrofitProvider.Type.Authenticated)) { createAuthenticatedRetrofit() }
}


private fun Scope.createBasicRetrofit() =
    get<RetrofitProvider>().provide(
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