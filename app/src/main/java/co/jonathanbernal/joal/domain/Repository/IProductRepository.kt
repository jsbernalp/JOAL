package co.jonathanbernal.joal.domain.Repository

import com.google.firebase.database.DataSnapshot
import io.reactivex.Observable

interface IProductRepository {
    fun getProductList():Observable<DataSnapshot>
}