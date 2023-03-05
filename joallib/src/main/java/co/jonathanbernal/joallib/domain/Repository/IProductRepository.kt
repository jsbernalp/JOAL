package co.jonathanbernal.joallib.domain.Repository

import com.google.firebase.database.DataSnapshot
import io.reactivex.Observable

interface IProductRepository {
    fun getProductList():Observable<DataSnapshot>
}