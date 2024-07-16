package com.example.productlist

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.productlist.DetailProductActivity.Companion.EXTRA_ID
import com.example.productlist.databinding.ActivityProductListBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ProductListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductListBinding

    //Creamos una variable, ya que vamos a hacer muchas búsquedas
    private lateinit var retrofit: Retrofit

    private lateinit var adapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupSearchView()
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
        //Como hemos puesto la función LAMBDA, aqui le pasaremos ese bloque de código que contiene la misma MIN 1h.55
        // adapter = ProductAdapter{productId -> navigateToDetail(productId)}
        adapter = ProductAdapter { navigateToDetail(it) }
        binding.rvProduct.setHasFixedSize(true)
        binding.rvProduct.layoutManager = LinearLayoutManager(this)
        binding.rvProduct.adapter = adapter
    }

    //Esta función nos devolverá el contenido de la API
    private fun searchByName(query: String) {
        //Mostramos el ProgressBar
        binding.progressBar.isVisible = true

        // Cuando busquemos, nuestro Retrofit , va a hacer la llamada a traves de la CoroutineScope
        // El IO es para cuando vayamos a hacer procesos MUY largos(peticiones de red, guardar en base de datos)
        CoroutineScope(Dispatchers.IO).launch {
            val myResponse: Response<ProductDataResponse> =
                retrofit.create(ApiService::class.java).getAllProducts(query)
            if (myResponse.isSuccessful) {
                Log.i("rolan", "funciona")
                //dentro del body es donde está la respuesta
                val response: ProductDataResponse? = myResponse.body()
                if (response != null) {
                    Log.i("rolan", response.toString())
                    runOnUiThread {
                        adapter.updateList(response.products)
                        binding.progressBar.isVisible = false
                    }

                }

            } else {
                Log.i("rolan", "No funciona")
            }
        }
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

    //Con esta función navegaremos a la pantalla Detail a traves del ID con una FUNCION LAMBDA
    private fun navigateToDetail(id: String) {
        val intent = Intent(this, DetailProductActivity::class.java)
        intent.putExtra(EXTRA_ID, id)
        startActivity(intent)
    }

    //Creamos un método y cambiamos el icono y las letras de color blanco del SearchView
    private fun setupSearchView() {
        val searchView: SearchView = binding.searchView
        searchView.queryHint = "Escribe el nombre del producto" // Añadir el hint

        val searchEditText = searchView.findViewById<EditText>(androidx.appcompat.R.id.search_src_text)
        searchEditText.setTextColor(Color.WHITE)
        searchEditText.setHintTextColor(Color.GRAY) // O el color que prefieras

        // Cambia el color del icono de búsqueda
        val searchIcon = searchView.findViewById<ImageView>(androidx.appcompat.R.id.search_mag_icon)
        searchIcon.setColorFilter(Color.WHITE)

        // Cambia el color del icono de cerrar (clear)
        val closeIcon = searchView.findViewById<ImageView>(androidx.appcompat.R.id.search_close_btn)
        closeIcon.setColorFilter(Color.WHITE)
    }
}