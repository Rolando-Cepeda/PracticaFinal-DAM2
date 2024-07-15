package com.example.productlist

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.productlist.databinding.ItemProductBinding
import com.squareup.picasso.Picasso

class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemProductBinding.bind(view)

    fun bind(productItemResponse: ProductItemResponse) {
        binding.tvProductName.text = productItemResponse.title
        //Al ser solo una lista le ponemos a la imagen [0], ya que como tal no existe la url
        Picasso.get().load(productItemResponse.images[0]).into(binding.ivProductImage)
    }
}