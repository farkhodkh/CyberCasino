package ru.cybercasino.android

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.cybercasino.android.system.di.appModule
import ru.cybercasino.feature.auth.di.appModuleAuth

/**
 * The main application class
 */
class CyberCasinoApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()

    }

    private fun initKoin() {
        startKoin {
            androidContext(this@CyberCasinoApp)
            modules(
                appModule,
                appModuleAuth
            )
        }
    }
}