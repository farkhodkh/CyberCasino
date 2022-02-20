package ru.cybercasino.feature.user.profile.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.cybercasino.feature.user.profile.viewmodel.UserProfileViewModel

/**
 * User profile module
 */
val appModuleMainProfile = module {
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
    viewModel { UserProfileViewModel() }
}