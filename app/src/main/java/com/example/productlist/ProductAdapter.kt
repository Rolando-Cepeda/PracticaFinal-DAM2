package com.example.productlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ProductAdapter(var productList: List<ProductDataResponse> = emptyList()) :
    RecyclerView.Adapter<ProductViewHolder>() {

      fun updateList(list: List<ProductDataResponse>){
          productList = list
          notifyDataSetChanged()
      }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ProductViewHolder(layoutInflater.inflate(R.layout.item_product, parent, false))
    }

    override fun getItemCount(): Int {
        return productList.size
    }
    // override fun getItemCount() = productList.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val item = productList[position]
        holder.bind(item)
        //holder.bind(productList[position])
        //
    }
}