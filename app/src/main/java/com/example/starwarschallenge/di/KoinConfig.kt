package com.example.starwarschallenge.di

import com.example.starwarschallenge.data.MovieRepositoryImpl
import com.example.starwarschallenge.domain.MovieRepository
import com.example.starwarschallenge.domain.MovieService
import com.example.starwarschallenge.ui.routes.movies.LightsabersViewModel
import com.example.starwarschallenge.ui.routes.movies.MoviesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object KoinConfig {
    val appModule = module {
        single<MovieRepository> { MovieRepositoryImpl }
        single<MovieService> { MovieService(get()) }
        viewModel { MoviesViewModel(get()) }
        viewModel { LightsabersViewModel() }
    }
}
