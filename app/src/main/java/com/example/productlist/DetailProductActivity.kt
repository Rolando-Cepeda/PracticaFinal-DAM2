package com.example.productlist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.productlist.databinding.ActivityDetailProductBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailProductActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ID = "extra_id"
    }

    private lateinit var binding: ActivityDetailProductBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Recuperamos la llamada a internet a traves de su ID.
        val id: String = intent.getStringExtra(EXTRA_ID).orEmpty()
        //Llamamos a un nuevo método, que es la que realizará la petición a internet, y le pasaremos el ID.
        //La petición a internet será similar a la que hicimos en el SEARCHVIEW.
        getProductInformation(id)
    }

    private fun getProductInformation(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val productDetail = getRetrofit().create(ApiService::class.java).getProducts(id)

            if(productDetail.body() != null){
                runOnUiThread { createUI(productDetail.body()!!) }

            }
        }
    }

    //Este método nos creará la vista
    private fun createUI(product: ProductDetailResponse) {
        Picasso.get().load(product.images[0]).into(binding.ivProductImageDetail)
        // Asignar el título al TextView correspondiente
        binding.tvTitle.text = product.title

        // Asignar la descripción al TextView correspondiente
        binding.tvDescription.text = product.description

        // Asignar la categoría al TextView correspondiente
        binding.tvCategory.text = product.category

        // Asignar el precio al TextView correspondiente
        binding.tvPrice.text = "€${product.price}"

        // Asignar el stock al TextView correspondiente
        binding.tvStock.text = "Stock: ${product.stock}"
    }

    //Creamos el retrofit.
    private fun getRetrofit(): Retrofit {
        val retrofit = Retrofit
            .Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit
        /*return retrofit = Retrofit
            .Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()*/
    }
}