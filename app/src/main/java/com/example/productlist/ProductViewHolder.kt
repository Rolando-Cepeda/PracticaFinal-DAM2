package com.example.productlist

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.productlist.databinding.ItemProductBinding
import com.squareup.picasso.Picasso

class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemProductBinding.bind(view)

    // También le pasamos la función LAMNDA
    fun bind(productItemResponse: ProductDetailResponse, onItemSelected: (String) -> Unit) {
        binding.tvProductName.text = productItemResponse.title
        binding.tvProductPrice.text = productItemResponse.price.toString()
        //Al ser solo una lista le ponemos a la imagen [0], ya que como tal no existe la url
        Picasso.get().load(productItemResponse.images[0]).into(binding.ivProductImage)
        // cada vez que alguien pulse en la vista llamaremos al método onItemSelected(le pasaremos la id del producto)
        binding.root.setOnClickListener { onItemSelected(productItemResponse.productId.toString()) }
    }
}