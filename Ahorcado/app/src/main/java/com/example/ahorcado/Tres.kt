package com.example.ahorcado

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.ViewGroup
import android.widget.Button
import android.widget.TableRow
import androidx.appcompat.app.AlertDialog
import com.example.ahorcado.databinding.ActivityTresBinding
import java.util.concurrent.TimeUnit

class Tres : AppCompatActivity() {
    private lateinit var binding: ActivityTresBinding
    private lateinit var countDownTimer: CountDownTimer

    private var tematica = Tematica()//Asigna una tematica ramdom
    private var puntos: Int = 0//Puntos que van aumentado a medida que juega
    private var vidas: Int = 8//Vidas del jugador

    private var palabraSecreta = RandomPalabra(tematica)//Asigna una palabra ramdom dependiendo de la palabra
    private var palabraAdivinada = CharArray(palabraSecreta.length) { '_' }//Asigna un guion a cada letra de la palabra secreta


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTresBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.Reiniciar.setOnClickListener {
            Reiniciar()
        }
        binding.Finalizar.setOnClickListener {
            Finalizar()
        }




        Tiempo()
        Informacion()
        countDownTimer.start()
        Jugar()
    }

    private fun Tiempo(){
        val intent = Intent(this, Tres::class.java)
        val intent2 = Intent(this, MainActivity::class.java)

        countDownTimer = object : CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                // Actualizar el TextView con el tiempo restante
                val minutesLeft = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)
                val secondsLeft = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished - TimeUnit.MINUTES.toMillis(minutesLeft))
                if (minutesLeft == 0L && secondsLeft == 0L) {
                    binding.txtTiempo.text = "Tiempo agotado"
                    onFinish()
                } else {
                    val timeLeft = String.format("%02d:%02d", minutesLeft, secondsLeft)
                    binding.txtTiempo.text = "$timeLeft"
                }
            }

            override fun onFinish() {
                // Mostrar una alerta cuando se acabe el tiempo
                val builder = AlertDialog.Builder(this@Tres)
                builder.setTitle("Tiempo agotado")
                builder.setMessage("Se te ha agotado el tiempo \uD83D\uDE2D \nLa palabra era $palabraSecreta")
                builder.setPositiveButton("Reiniciar") { dialog, which ->
                    startActivity(intent)
                }
                builder.setNegativeButton("Salir") { dialog, which ->
                    startActivity(intent2)
                }
                builder.show()
            }
        }
    }
    private fun Informacion() {
        binding.txtPuntos.text = "Points: " + this.puntos.toString()
        binding.txtTematica.text = this.tematica
        binding.txtVidas.text = "♥ " + this.vidas.toString()
    }
    private fun Tematica():String{
        val tematica = arrayOf("Animals","Cities","Sports","Fruit")
        var seleccionar = (0..3).random()
        return tematica[seleccionar];
    }
    private fun Jugar(){
        binding.palabraAdivinadaTextView.text =palabraAdivinada.joinToString(" ")
        var filas: Int = 4
        var columnas: Int =8
        val letrasTeclado = listOf("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M","N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y","Z")

        for (i in 1..filas) {
            val tabla = TableRow(binding.teclado.context)
            tabla.layoutParams = TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT
            )

            for (j in 1.. columnas) {
                val index = (i-1) * columnas + j - 1
                val boton = Button(binding.teclado.context)


                if (index >= letrasTeclado.size) {
                    break // Salir del ciclo si se ha llegado al final de la lista
                }


                boton.text = letrasTeclado[index]
                boton.layoutParams = TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT
                )

                Propiedades(boton)
                tabla.addView(boton)

                boton.setOnClickListener{
                    val letraIngresada= boton.text.toString().firstOrNull()

                    if(letraIngresada != null){
                        if(palabraSecreta.contains(letraIngresada)){
                            for(i in palabraSecreta.indices){
                                if(palabraSecreta[i] == letraIngresada){
                                    palabraAdivinada[i] = letraIngresada
                                }

                            }
                            binding.palabraAdivinadaTextView.text =palabraAdivinada.joinToString(" ")
                            puntos = puntos + 100;

                            if (!palabraAdivinada.contains('_')) {
                                Ganador()
                            }

                        }else{
                            if(vidas==8){
                                val imageResource = resources.getIdentifier("imagen1","drawable",packageName)
                                binding.imagen.setImageResource(imageResource)
                                vidas--
                            }else{
                                if(vidas==7){
                                    val imageResource = resources.getIdentifier("imagen2","drawable",packageName)
                                    binding.imagen.setImageResource(imageResource)
                                    vidas--
                                }else{
                                    if(vidas==6){
                                        val imageResource = resources.getIdentifier("imagen3","drawable",packageName)
                                        binding.imagen.setImageResource(imageResource)
                                        vidas--
                                    }else{
                                        if(vidas==5){
                                            val imageResource = resources.getIdentifier("imagen4","drawable",packageName)
                                            binding.imagen.setImageResource(imageResource)
                                            vidas--
                                        }else{
                                            if(vidas==4){
                                                val imageResource = resources.getIdentifier("imagen5","drawable",packageName)
                                                binding.imagen.setImageResource(imageResource)
                                                vidas--
                                            }else{
                                                if(vidas==3){
                                                    val imageResource = resources.getIdentifier("imagen6","drawable",packageName)
                                                    binding.imagen.setImageResource(imageResource)
                                                    vidas--
                                                }else{
                                                    if(vidas==2){
                                                        val imageResource = resources.getIdentifier("imagen7","drawable",packageName)
                                                        binding.imagen.setImageResource(imageResource)
                                                        vidas--
                                                    }else{
                                                        if(vidas==1){
                                                            val imageResource = resources.getIdentifier("imagen8","drawable",packageName)
                                                            binding.imagen.setImageResource(imageResource)
                                                            vidas--
                                                            Perdedor()
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }

                                }
                            }
                        }
                        boton.isEnabled=false;
                        boton.setBackgroundResource(R.color.white)
                    }
                    Informacion()
                }
            }
            binding.teclado.addView(tabla)
        }
    }
    private fun RandomPalabra(tematica:String):String{
        val frutas= arrayOf("APPLE", "BANANA", "ORANGE", "STRAWBERRY", "GRAPE", "PINEAPPLE", "MANGO", "WATERMELON", "KIWI", "BLUEBERRY")
        val animales = arrayOf("LION", "TIGER", "ELEPHANT", "MONKEY", "GORILLA", "GIRAFFE", "ZEBRA", "HIPPOPOTAMUS", "CROCODILE", "PYTHON")
        val ciudades = arrayOf("NEWYORK", "LONDON", "PARIS", "TOKYO", "BEIJING", "MOSCOW", "SYDNEY", "TORONTO", "BERLIN", "DUBAI")
        val deportes = arrayOf("BASKETBALL", "FOOTBALL", "SOCCER", "TENNIS", "GOLF", "SWIMMING", "CYCLING", "RUNNING", "BOXING", "VOLLEYBALL")
        var seleccionar = (0..9).random()

        if(tematica == "Fruit"){
            return frutas[seleccionar]
        }else{
            if(tematica == "Animals"){
                return animales[seleccionar]
            }else{
                if(tematica == "Cities"){
                    return ciudades[seleccionar]
                }else{
                    return deportes[seleccionar]
                }
            }
        }

    }
    private fun Propiedades(boton: Button){
        boton.layoutParams = TableRow.LayoutParams(120, 120)// Tamaño de los botones
        val layoutParams = boton.layoutParams as ViewGroup.MarginLayoutParams
        layoutParams.setMargins(8, 8, 8, 8) // Establece las margenes
        boton.setBackgroundResource(R.color.black)
    }
    fun Reiniciar(){
        val dialogo = AlertDialog.Builder(this)
        val intent = Intent(this, Info3::class.java)

        dialogo.setTitle("Esta seguro de reiniciar el juego presione OK")
        dialogo.setPositiveButton("OK") { dialog, which ->// Este código se ejecutará cuando se presione el botón "OK"
            startActivity(intent)
        }
        val dialog = dialogo.create()
        dialog.show()
    }
    fun Finalizar(){
        val dialogo = AlertDialog.Builder(this)
        val intent = Intent(this, MainActivity::class.java)

        dialogo.setTitle("Si esta seguro de salir presione OK")
        dialogo.setPositiveButton("OK") { dialog, which ->// Este código se ejecutará cuando se presione el botón "OK"
            startActivity(intent)
        }
        val dialog = dialogo.create()
        dialog.show()
    }
    fun Ganador(){
        var intent = Intent(this, Ganador3::class.java)
        intent.putExtra("puntos", puntos)
        intent.putExtra("palabra", palabraSecreta)
        startActivity(intent)
    }
    fun Perdedor(){
        var intent = Intent(this, Perdedor::class.java)
        intent.putExtra("puntos", puntos)
        intent.putExtra("palabra", palabraSecreta)
        startActivity(intent)
    }
}