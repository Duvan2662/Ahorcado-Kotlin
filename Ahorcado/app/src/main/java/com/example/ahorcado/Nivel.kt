package com.example.ahorcado

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ahorcado.databinding.ActivityNivelBinding

class Nivel : AppCompatActivity() {
    private lateinit var binding: ActivityNivelBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNivelBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.nivel1.setOnClickListener {
            uno()
        }
        binding.nivel2.setOnClickListener {
            dos()
        }
        binding.nivel3.setOnClickListener {
            tres()
        }
    }


    fun uno(){
        var intent = Intent(this, Info1::class.java)
        startActivity(intent)
    }//Funcion que me lleva al nivel 1
    fun dos(){
        var intent = Intent(this, Info2::class.java)
        startActivity(intent)
    }//Funcion que me lleva al nivel 2
    fun tres(){
        var intent = Intent(this, Info3::class.java)
        startActivity(intent)
    }//Funcion que me lleva al nivel 3

}