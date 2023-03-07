package co.jonathanbernal.joallib.di

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
object JoalLlibModule {

    @Provides
    @Singleton
    fun provideViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory = factory

    @Provides
    @Singleton
    fun provideDatabaseReference(): DatabaseReference = Firebase.database.reference


    @Provides
    @Singleton
    fun provideProductRepository(databaseReference: DatabaseReference): IProductRepository {
        return ProductRepository(databaseReference)
    }
}