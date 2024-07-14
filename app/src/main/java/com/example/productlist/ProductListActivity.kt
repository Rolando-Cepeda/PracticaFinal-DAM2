package com.example.productlist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.example.productlist.databinding.ActivityProductListBinding
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ProductListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductListBinding

    private lateinit var retrofit: Retrofit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        retrofit = getRetrofit() // Para hacer las LLAMADAS debemos llamar a retrofit.
        initUI()
    }

    private fun initUI() {
        //Cuando yo escriba y pulso el BOTón de buscar ahi hará la llamada
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            //Esta función se activará cuando demos al botón de buscar MIN 16:31
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchByName(query.orEmpty())
                return false
            }

            // Esta se llamará, cada vez que vayamos escribiendo, pero no la utlizaremos.
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    //Esta función nos devolverá el contenido de la API
    private fun searchByName(query: String) {

    }

    //OBJETO RETROFIT para hacer las llamadas
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