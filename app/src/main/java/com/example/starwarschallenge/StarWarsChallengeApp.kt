package com.example.starwarschallenge

import android.app.Application
import com.example.starwarschallenge.di.KoinConfig.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class StarWarsChallengeApp : Application() {
    companion object {
        lateinit var appContext: StarWarsChallengeApp
            private set
    }

    override fun onCreate() {
        super.onCreate()
        appContext = this
        startKoin {
            // Set the Android context for Koin
            androidContext(this@StarWarsChallengeApp)
            // Set up the app module
            modules(
                appModule,
            )
        }
    }
}
