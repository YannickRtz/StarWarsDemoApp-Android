package com.example.starwarschallenge.di

import com.example.starwarschallenge.ui.routes.movies.MoviesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object KoinConfig {
    val appModule = module {
        viewModel { MoviesViewModel() }
    }
}
