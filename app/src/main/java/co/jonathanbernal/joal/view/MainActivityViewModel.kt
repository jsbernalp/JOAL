package co.jonathanbernal.joal.view

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModel
import co.jonathanbernal.joal.domain.Product
import co.jonathanbernal.joal.domain.UseCase.GetProductListUseCase
import co.jonathanbernal.joal.ext.addTo
import com.google.firebase.database.DatabaseReference
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
    private val getProductListUseCase: GetProductListUseCase
) : ViewModel(){

    val disposables = CompositeDisposable()

    @SuppressLint("CheckResult")
    fun getProducts(){
        getProductListUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                when(it){
                is GetProductListUseCase.Result.Success->{
                    if (it.list.exists()){
                        Log.e("MainActivityViewModel","respuesta correcta ${it.list.children}")
                        val snapshot = it.list.children
                        snapshot.forEach {child->
                            val id = child.child("id").value.toString()
                            val nombre = child.child("nombre").value.toString()
                            val product = Product(id,nombre)
                            Log.e("MainActivityViewModel","este es el producto = $product")
                        }
                    }else{
                        Log.e("MainActivityViewModel","respuesta correcta pero no tiene datos")
                    }
                }
                is GetProductListUseCase.Result.Failure->{
                    Log.e("MainActivityViewModel","error en la peticion")

                }
                else ->  Log.e("MainActivityViewModel","error desconocido, por favor revisar")
            }
            }
            .addTo(disposables)
    }

}