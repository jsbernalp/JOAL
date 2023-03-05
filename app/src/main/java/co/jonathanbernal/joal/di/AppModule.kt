package co.jonathanbernal.joal.di

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import co.jonathanbernal.joallib.domain.Repository.IProductRepository
import co.jonathanbernal.joallib.domain.Repository.ProductRepository
import co.jonathanbernal.joallib.presentation.viewmodel.ViewModelFactory
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


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


    @Provides
    @Singleton
    @JvmStatic
    fun provideViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory = factory


    @Provides
    @Singleton
    @JvmStatic
    fun provideDatabaseReference(): DatabaseReference = Firebase.database.reference


    @Provides
    @Singleton
    @JvmStatic
    fun provideProductRepository(databaseReference: DatabaseReference): IProductRepository {
        return ProductRepository(databaseReference)
    }


}