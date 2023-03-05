package co.jonathanbernal.joallib.presentation.viewmodel


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.jonathanbernal.joallib.R
import co.jonathanbernal.joallib.domain.Product
import co.jonathanbernal.joallib.domain.UseCase.GetProductListUseCase
import co.jonathanbernal.joallib.ext.addTo
import co.jonathanbernal.joallib.presentation.view.ProductsAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
    private val getProductListUseCase: GetProductListUseCase
) : ViewModel(){

    var productsToShow: MutableLiveData<List<Product>> = MutableLiveData()
    var listAdapter: ProductsAdapter? = null
    val productList = ArrayList<Product>()
    val disposables = CompositeDisposable()

    fun getProducts(){
        getProductListUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                when(it){
                is GetProductListUseCase.Result.Success->{
                    if (it.list.exists()){
                        productList.clear()
                        val snapshot = it.list.children
                        Log.e("MainActivityViewModel","respuesta correcta $snapshot")
                        snapshot.forEach {child->
                            val product = child.getValue(Product::class.java)
                            product?.let { it1 -> productList.add(it1) }
                        }
                        productsToShow.postValue(productList)
                    }else{
                        Log.e("MainActivityViewModel","respuesta correcta pero no tiene datos")
                        productList.clear()
                        productsToShow.postValue(productList)
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

    fun setData(list: List<Product>) {
        listAdapter?.setProductList(list)
    }

    fun getProduct(position: Int): Product?{
        val products: MutableLiveData<List<Product>> = productsToShow
        return products.value?.get(position)
    }

    fun getRecyclerProductAdapter(): ProductsAdapter?{
        listAdapter = ProductsAdapter(this, R.layout.cell_product)
        return listAdapter
    }


}