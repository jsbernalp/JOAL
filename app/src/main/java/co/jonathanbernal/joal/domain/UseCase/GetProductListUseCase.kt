package co.jonathanbernal.joal.domain.UseCase

import co.jonathanbernal.joal.domain.Repository.IProductRepository
import com.google.firebase.database.DataSnapshot
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class GetProductListUseCase @Inject constructor(private val iProductRepository: IProductRepository){

    sealed class Result {
        object Loading : Result()
        data class Success(val list: DataSnapshot) : Result()
        data class Failure(val throwable: Throwable) : Result()
    }

    fun execute(): Observable<Result> {
        return iProductRepository.getProductList()
                .map { Result.Success(it) as Result}
                .onErrorReturn { Result.Failure(it) }

    }

}