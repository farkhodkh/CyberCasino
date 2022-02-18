package ru.cybercasino.feature.user.profile.di

import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

/**
 * User profile module
 */
val appModuleAuth = module {
//    factory { ApiHeadersInterceptor() }
//    single<RetrofitProvider> { RetrofitProviderImpl() }
//    single<SecurityHelper> { SecurityHelperImpl(Dispatchers.Default) }
//    single<AuthenticationStorageRepository> { AuthenticationStorageRepositoryImpl(androidContext(), get()) }
//    single(named(RetrofitProvider.Type.Basic)) { createBasicRetrofit() }
//    single {
//        LoginController(
//            get<Retrofit>(named(RetrofitProvider.Type.Basic)).create(
//                AuthenticationApi::class.java
//            )
//        )
//    }
//    viewModel { LoginScreenViewModel(get(), get()) }
}