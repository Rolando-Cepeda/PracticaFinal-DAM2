package com.example.productlist

import com.google.gson.annotations.SerializedName

class ProductDetailResponse(
    @SerializedName("id") val productId: Int,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("category") val category: String,
    @SerializedName("price") val price: Double,
    @SerializedName("stock") val stock: Int,
    //Al ser una lista de imágenes le ponemos List<String>
    @SerializedName("images") val images: List<String>
)