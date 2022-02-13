package ru.cybercasino.android.system.di

import org.koin.dsl.module
import ru.cybercasino.service_network.interceptors.ApiHeadersInterceptor
import ru.cybercasino.service_network.retrofit.RetrofitProvider
import ru.cybercasino.service_network.retrofit.RetrofitProviderImpl

/**
 * The application module.
 */
val appModule = module {

    factory { ApiHeadersInterceptor() }
    single<RetrofitProvider> { RetrofitProviderImpl() }

//    single {
//        LoginController(
//            CoroutineScope(Dispatchers.Default),
//            get<Retrofit>(named(Environment.Basic)).create(
//                AuthenticationApi::class.java
//            )
//        )
//    }
//
//    single(named(Environment.Basic.buildType)) { createBasicRetrofit() }
//
//    viewModel { LoginScreenViewModel(get()) }
//    single(named(Authenticated)) { createAuthenticatedRetrofit() }

}

//private fun Scope.createBasicRetrofit() =
//    get<RetrofitProvider>().provide(
//        interceptors = listOf(
//            get<ApiHeadersInterceptor>(),
//        )
//    )