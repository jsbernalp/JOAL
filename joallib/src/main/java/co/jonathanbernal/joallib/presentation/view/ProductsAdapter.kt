package co.jonathanbernal.joallib.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import co.jonathanbernal.joallib.BR
import co.jonathanbernal.joallib.domain.Product
import co.jonathanbernal.joallib.presentation.viewmodel.MainActivityViewModel

class ProductsAdapter internal constructor(var mainActivityViewModel: MainActivityViewModel, var resource: Int) :
    RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    private var productListAdapter: List<Product> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding =
            DataBindingUtil.inflate(layoutInflater, viewType, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setProductCard(mainActivityViewModel, position)
    }

    override fun getItemViewType(position: Int): Int {
        return getLayoutIdForPosition(position)
    }

    private fun getLayoutIdForPosition(position: Int): Int {
        return resource
    }

    override fun getItemCount(): Int {
        return productListAdapter.size
    }

    fun setProductList(products: List<Product>) {
        this.productListAdapter = products
        notifyDataSetChanged()
    }

    class ViewHolder(binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        private var binding: ViewDataBinding? = null

        init {
            this.binding = binding
        }

        fun setProductCard(mainActivityViewModel: MainActivityViewModel, position: Int) {
            binding?.setVariable(BR.itemProduct, mainActivityViewModel)
            binding?.setVariable(BR.position, position)
            binding?.executePendingBindings()
        }

    }
}