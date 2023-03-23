package com.example.ahorcado

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ahorcado.databinding.ActivityInfo3Binding

class Info3 : AppCompatActivity() {
    private lateinit var binding: ActivityInfo3Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityInfo3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        cargar()
        binding.iniciar.setOnClickListener {
            irlvl()
        }
    }
    //Carga la imagen del nivel 3
    private fun cargar(){
        val imageResource = resources.getIdentifier("lvl3","drawable",packageName)
        binding.imagen.setImageResource(imageResource)
    }

    //Va al nivel 3
    private fun irlvl(){
        var intent = Intent(this, Tres::class.java)
        startActivity(intent)
    }
}