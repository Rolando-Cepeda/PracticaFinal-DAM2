package com.example.productlist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class DetailProductActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_ID = "extra_id"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_product)

    }
}