package com.elmasry.dkbphotos

import android.app.Application
import com.elmasry.dkbphotos.di.Dependencies
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class PhotosApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            if (BuildConfig.DEBUG) {
                // use AndroidLogger as Koin Logger - default Level.INFO
                androidLogger()
            }

            // use the Android context given there
            androidContext(this@PhotosApplication)

            // module list
            modules(Dependencies.get())
        }
    }
}
