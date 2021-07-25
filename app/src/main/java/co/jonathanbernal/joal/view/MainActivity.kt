package co.jonathanbernal.joal.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import co.jonathanbernal.joal.R
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector : DispatchingAndroidInjector<Any>

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var mainActivityViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupBinding()
    }

    private fun setupBinding() {
        mainActivityViewModel = ViewModelProviders.of(this,viewModelFactory).get(mainActivityViewModel::class.java)
        mainActivityViewModel.getProducts()
    }


    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector


}