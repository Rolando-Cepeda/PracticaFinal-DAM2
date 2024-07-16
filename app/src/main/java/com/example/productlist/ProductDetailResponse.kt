package com.example.productlist

import com.google.gson.annotations.SerializedName

class ProductDetailResponse(
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("category") val category: String,
    @SerializedName("price") val price: Double,
    @SerializedName("stock") val stock: Int
)