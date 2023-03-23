package com.example.ahorcado

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ahorcado.databinding.ActivityInfo2Binding

class Info2 : AppCompatActivity() {
    private lateinit var binding:ActivityInfo2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfo2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        cargar()
        binding.iniciar.setOnClickListener {
            irlvl()
        }

    }


    //Carga la imagen del nivel 2
    private fun cargar(){
        val imageResource = resources.getIdentifier("lvl2","drawable",packageName)
        binding.imagen.setImageResource(imageResource)
    }

    //Va al nivel 2
    private fun irlvl(){
        var intent = Intent(this, Dos::class.java)
        startActivity(intent)
    }
}