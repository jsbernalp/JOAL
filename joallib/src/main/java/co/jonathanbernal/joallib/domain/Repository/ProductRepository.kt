package co.jonathanbernal.joallib.domain.Repository

import android.util.Log
import com.androidhuman.rxfirebase2.database.RxFirebaseDatabase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import io.reactivex.Observable

class ProductRepository(
    private val databaseReference: DatabaseReference
): IProductRepository {

    override fun getProductList(): Observable<DataSnapshot> {
        Log.e("ProductRepository","Ingresoooooo")
       return RxFirebaseDatabase.dataChanges(databaseReference.child("products"))
    }

}