package co.jonathanbernal.joal.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object AppModule {


    @Singleton
    @Provides
    @JvmStatic
    fun provideContext(application: JoalApplication): Context = application.applicationContext

    @Singleton
    @Provides
    @JvmStatic
    fun provideApplication(application: JoalApplication): Application = application
}