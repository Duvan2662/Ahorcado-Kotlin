package com.example.ahorcado

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ahorcado.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.jugar.setOnClickListener {
            nivel()
        }

    }


    fun nivel(){
        var intent = Intent(this, Nivel::class.java)
        startActivity(intent)
    }//Funcion que me lleva escoger nivel

}