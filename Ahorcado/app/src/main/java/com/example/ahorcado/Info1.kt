package com.example.ahorcado

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ahorcado.databinding.ActivityInfo1Binding


class Info1 : AppCompatActivity() {
    private lateinit var binding: ActivityInfo1Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfo1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        cargar()
        binding.iniciar.setOnClickListener {
            irlvl()
        }
    }

    private fun cargar(){
        val imageResource = resources.getIdentifier("lvl1","drawable",packageName)
        binding.imagen.setImageResource(imageResource)
    }//Carga la imagen del nivel 1
    private fun irlvl(){
        var intent = Intent(this, Uno::class.java)
        startActivity(intent)
    }//Va al nivel 1
}