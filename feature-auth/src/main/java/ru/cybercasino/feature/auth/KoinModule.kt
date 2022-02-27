package ru.cybercasino.feature.auth

import kotlinx.coroutines.GlobalScope
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
//import ru.cybercasino.feature_auth_api.AuthenticationApi
//import ru.cybercasino.network.RetrofitProvider
//
/**
 * Auth module provides authorization components.
 */
val authModule = module {

//    // API
//    single<AuthenticationApi> { get<Retrofit>(named(RetrofitProvider.Type.Basic)).create(AuthenticationApi::class.java) }
//
//    single { LoginController(GlobalScope, get()) }
//    // Interceptors
//    factory { AuthInterceptor(get()) }
//    factory { ClientUuidInterceptor(get()) }
//
//    // Authenticator
//    single { ApiAuthenticator(get(), get()) }

}