package ru.cybercasino.feature.main.profile.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.cybercasino.feature.main.profile.viewmodel.MainProfileViewModel

/**
 * Main profile module
 */
val mainProfileModule = module {
    viewModel { MainProfileViewModel() }
}
