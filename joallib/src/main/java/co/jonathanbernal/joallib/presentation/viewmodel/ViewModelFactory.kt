package co.jonathanbernal.joallib.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class ViewModelFactory @Inject constructor(
        private val mainActivityViewModel: MainActivityViewModel
): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(mainActivityViewModel::class.java)){
            return mainActivityViewModel as T
        }
        throw IllegalArgumentException("uknown class name")
    }
}