package com.example.productlist

import com.google.gson.annotations.SerializedName

// eSTA DATA CLASS será la respuesta a la llamada.
data class ProductDataResponse (
    // @SerializedName("products").- Hará referencia al nombre tal y com está en el JSON
    @SerializedName("products") val products: String)