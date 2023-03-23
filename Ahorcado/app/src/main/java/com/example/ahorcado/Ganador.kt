package com.example.ahorcado

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ahorcado.databinding.ActivityGanadorBinding


class Ganador : AppCompatActivity() {
    private lateinit var binding: ActivityGanadorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGanadorBinding.inflate(layoutInflater)
        setContentView(binding.root)




        cargarDatos();
        binding.pasar.setOnClickListener {
            siguiente()
        }
        binding.inicio.setOnClickListener {
            inicio()
        }
    }


    //Carga los datos de la activity Ganador
    private fun cargarDatos(){
        val imageResource = resources.getIdentifier("ganaste1","drawable",packageName)//Imagen del ganador
        binding.imagen.setImageResource(imageResource)//Coloca la imagen en la imagen del layout
        binding.puntos.text = "Puntos: " + intent.getIntExtra("puntos",0)//Recibe los puntos del juego anterior y los coloca
        binding.intentos.text = "Palabra: " + intent.getStringExtra("palabra")//Recibe la palabra del juego anterior y la coloca
    }
    //Inicia el nivel 2
    private fun siguiente(){
        var intent = Intent(this, Info2::class.java)
        startActivity(intent)
    }
    //Vuelve al inicio
    private fun inicio(){//Vuelve al inicio
        var intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}