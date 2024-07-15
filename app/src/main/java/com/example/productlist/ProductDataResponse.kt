package com.example.productlist

import com.google.gson.annotations.SerializedName

// eSTA DATA CLASS será la respuesta a la llamada.
/*data class ProductDataResponse(
    // @SerializedName().- Hará referencia al nombre tal y com está en el JSON
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("price") val price: Double
)*/

data class ProductDataResponse(
    //.- Hará referencia al nombre tal y como está en el JSON
    @SerializedName("products") val products: List<ProductItemResponse>
)

data class ProductItemResponse(
    @SerializedName("id") val productId: Int,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("price") val price: Double,
    //Al ser una lista de imágenes le ponemos List<String>
    @SerializedName("images") val images: List<String>
)
