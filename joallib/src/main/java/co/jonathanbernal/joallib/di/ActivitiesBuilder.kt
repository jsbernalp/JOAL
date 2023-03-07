package co.jonathanbernal.joallib.di

import co.jonathanbernal.joallib.presentation.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesBuilder {

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

}