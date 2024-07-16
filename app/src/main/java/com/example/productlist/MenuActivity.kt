package com.example.productlist

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        val botonInicio = findViewById<Button>(R.id.btnInicio)
        botonInicio.setOnClickListener { navigateToProductInicio() }
    }

    private fun navigateToProductInicio() {

        val intent = Intent(this, ProductListActivity::class.java)
        startActivity((intent))
        //Mostramos un diálogo, en respuesta a la acción, en cuánto el usuario haga click en el botón.
        Toast.makeText(this, "Navegando a la lista de productos", Toast.LENGTH_SHORT).show()

    }
}