package co.jonathanbernal.joal.di

import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class JoalApplication: Application(),HasAndroidInjector {
    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Any>


    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        initComponent()
    }

    private fun initComponent() {
        component = DaggerAppComponent.builder()
            .application(this)
            .build()

        component.inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any>  = activityInjector
}