package ru.cybercasino.android.system.di

import org.koin.core.scope.Scope
import org.koin.dsl.module
import ru.cybercasino.service_network.interceptors.ApiHeadersInterceptor
import ru.cybercasino.service_network.retrofit.RetrofitProvider

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