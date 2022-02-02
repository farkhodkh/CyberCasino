package ru.cybercasino.android.system.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.cybercasino.feature.auth.viewmodel.LoginScreenViewModel

/**
 * The application module.
 */
val appModule = module {
//    factory<NavigationIntentProvider> { NavigationIntentProviderImpl() }
//
//    single(named(Basic)) { createBasicRetrofit() }
//    single(named(Authenticated)) { createAuthenticatedRetrofit() }

    viewModel { LoginScreenViewModel() }
}