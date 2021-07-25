package co.jonathanbernal.joal.di

import co.jonathanbernal.joal.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesBuilder {

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

}