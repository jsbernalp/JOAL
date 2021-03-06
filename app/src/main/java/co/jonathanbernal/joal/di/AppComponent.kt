package co.jonathanbernal.joal.di

import android.app.Application
import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(
    modules = [AndroidSupportInjectionModule::class,
    AppModule::class,
    ActivitiesBuilder::class])
@Singleton
interface AppComponent: AndroidInjector<JoalApplication> {

    fun context():Context
    fun application():Application

    @Component.Builder
    interface  Builder{

        fun build():AppComponent

        @BindsInstance
        fun application(application: JoalApplication):Builder
    }
}