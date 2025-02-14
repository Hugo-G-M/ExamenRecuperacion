package com.example.examenrecuperacion

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class actividad3: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_3)

        val botonVolver = findViewById<Button>(R.id.button5)
        botonVolver.setOnClickListener {
            val intent = Intent(this, actividad2::class.java)
            startActivity(intent)
        }
    }
}