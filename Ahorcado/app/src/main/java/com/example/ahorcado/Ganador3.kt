package com.example.ahorcado

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ahorcado.databinding.ActivityGanador3Binding

class Ganador3 : AppCompatActivity() {
    private lateinit var binding:ActivityGanador3Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGanador3Binding.inflate(layoutInflater)
        setContentView(binding.root)




        cargarDatos();
        binding.inicio.setOnClickListener {
            inicio()
        }
    }
    private fun cargarDatos(){
        val imageResource = resources.getIdentifier("ganaste3","drawable",packageName)
        binding.imagen.setImageResource(imageResource)
        binding.puntos.text = "Puntos: " + intent.getIntExtra("puntos",0)
        binding.intentos.text = "Palabra: " + intent.getStringExtra("palabra")
    }
    private fun inicio(){
        var intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}