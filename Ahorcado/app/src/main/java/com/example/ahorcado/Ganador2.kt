package com.example.ahorcado

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ahorcado.databinding.ActivityGanador2Binding

class Ganador2 : AppCompatActivity() {
    private lateinit var binding:ActivityGanador2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGanador2Binding.inflate(layoutInflater)
        setContentView(binding.root)




        cargarDatos();
        binding.pasar.setOnClickListener {
            siguiente()
        }
        binding.inicio.setOnClickListener {
            inicio()
        }
    }

    private fun cargarDatos(){
        val imageResource = resources.getIdentifier("ganaste2","drawable",packageName)
        binding.imagen.setImageResource(imageResource)
        binding.puntos.text = "Puntos: " + intent.getIntExtra("puntos",0)
        binding.intentos.text = "Palabra: " + intent.getStringExtra("palabra")
    }

    private fun siguiente(){
        var intent = Intent(this, Info3::class.java)
        startActivity(intent)
    }

    private fun inicio(){
        var intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}