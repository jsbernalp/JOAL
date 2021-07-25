package co.jonathanbernal.joal.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import co.jonathanbernal.joal.R
import co.jonathanbernal.joal.databinding.ActivityMainBinding
import co.jonathanbernal.joal.presentation.viewmodel.MainActivityViewModel
import co.jonathanbernal.joal.presentation.viewmodel.ViewModelFactory
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

    lateinit var binding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        setupBinding()
    }


    private fun setupBinding() {
        mainActivityViewModel = ViewModelProvider(this,viewModelFactory).get(mainActivityViewModel::class.java)
        binding.mainList = mainActivityViewModel
        mainActivityViewModel.getProducts()
        mainActivityViewModel.productsToShow.observe(this,{
            Log.e("MainActivity","listado de productos en las vista $it")
            mainActivityViewModel.setData(it)
        })
    }


    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector


}