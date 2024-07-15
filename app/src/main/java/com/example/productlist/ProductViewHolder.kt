package com.example.productlist

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.productlist.databinding.ItemProductBinding

class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view){

    private val binding = ItemProductBinding.bind(view)

    fun bind(productDataResponse:ProductDataResponse){
binding.tvProductName.text = productDataResponse.title
    }
}