package com.example.productlist

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


// No se mete CÓDIGO, metemos como el CONTRATO(le decimos que llamadas va a hacer implementando esta interfaz.)
interface ApiService {

    //Especificamos el tipo de llamada que vamos a hacer

    @GET("/products/{id}") // Especificamos la ruta del GETPOINT
    suspend fun getProducts(@Path("id") productId: String) : Response<ProductDataResponse>

    @GET("products")
    suspend fun getAllProducts(): Response<ProductDataResponse>
}