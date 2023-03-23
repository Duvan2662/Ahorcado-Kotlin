package com.example.ahorcado

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.ViewGroup
import android.widget.Button
import android.widget.TableRow
import androidx.appcompat.app.AlertDialog
import com.example.ahorcado.databinding.ActivityUnoBinding
import java.util.concurrent.TimeUnit

class Uno : AppCompatActivity() {
    private lateinit var binding: ActivityUnoBinding
    private lateinit var countDownTimer: CountDownTimer



    private var tematica = Tematica()//Asigna una tematica ramdom
    private var puntos: Int = 0//Puntos que van aumentado a medida que juega
    private var vidas: Int = 8//Vidas del jugador

    private var palabraSecreta = RandomPalabra(tematica)//Asigna una palabra ramdom dependiendo de la palabra
    private var palabraAdivinada = CharArray(palabraSecreta.length) { '_' }//Asigna un guion a cada letra de la palabra secreta



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUnoBinding.inflate(layoutInflater)
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

    //Funcion Tiempo
    private fun Tiempo(){
        val intent = Intent(this, Uno::class.java)//Actividad Uno
        val intent2 = Intent(this, MainActivity::class.java)//Actividad Inicial

        countDownTimer = object : CountDownTimer(240000, 1000) {//Defino el tiempo
            override fun onTick(millisUntilFinished: Long) {
            //Pasa el tiempo a minutos y segundos
                val minutesLeft = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)
                val secondsLeft = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished - TimeUnit.MINUTES.toMillis(minutesLeft))
            //Verifica si se acabo el tiempo
            if (minutesLeft == 0L && secondsLeft == 0L) {
                    binding.txtTiempo.text = "Tiempo agotado"//Actualiza el cuadro de texto si se termina el tiempo
                    onFinish()//Funcion que tiene un mensaje emergente
                } else {
                    val timeLeft = String.format("%02d:%02d", minutesLeft, secondsLeft)//Formato en minutos y segundos
                    binding.txtTiempo.text = "$timeLeft"//Actualiza el cuadro de texto con el tiempo
                }
            }

            //Funcion si se acabo el tiempo
            override fun onFinish() {
                // Mostrar una alerta cuando se acabe el tiempo
                val builder = AlertDialog.Builder(this@Uno)//Se muestra en la actividad 1
                builder.setTitle("Tiempo agotado")//Titulo de la ventana
                builder.setMessage("Se te ha agotado el tiempo \uD83D\uDE2D \nLa palabra era $palabraSecreta")//Mensaje de la ventana
                builder.setPositiveButton("Reiniciar") { dialog, which ->//Boton reiniciar
                    startActivity(intent)//Vuelve a reiniciar el nivel actual
                }
                builder.setNegativeButton("Salir") { dialog, which ->//Boton Salir
                    startActivity(intent2)//Vuelve a la pagina pricipal MainActiviti
                }
                builder.show()
            }
        }
    }


    //Informacion que se va actualizando
    private fun Informacion() {
        binding.txtPuntos.text = "Puntos: " + this.puntos.toString()//Actualiza los puntos
        binding.txtTematica.text = this.tematica//Muestra la tematica
        binding.txtVidas.text = "♥ " + this.vidas.toString()//Muestra las oportunidaades restantes
    }

    //Tematica
    private fun Tematica():String{
        val tematica = arrayOf("Animales","Ciudades","Deportes","Frutas")//Arreglo de tematicas
        var seleccionar = (0..3).random()//Numero aleatorio entre 0-3
        return tematica[seleccionar];//Selecciona una tematica del vector con el numero aleatorio
    }


    //Funcion jugar
    private fun Jugar(){
        binding.palabraAdivinadaTextView.text =palabraAdivinada.joinToString(" ")//Le da un espacio al arreglo de caracteres _ _ _
        var filas: Int = 4//Filas de mi teclado
        var columnas: Int =8// Columnas de mi teclado
        val letrasTeclado = listOf("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M","N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y","Z")//Arreglo de letras del teclado

        for (i in 1..filas) {//Arreglo que recorre las filas de la tabla
            val tabla = TableRow(binding.teclado.context)//Se crea una variable tabla que contiene el Table Layout
            tabla.layoutParams = TableRow.LayoutParams(//Parametros de la tabala
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT
            )

            for (j in 1.. columnas) {//Arreglo que recorre las columnas de la tabla
                val index = (i-1) * columnas + j - 1//Esta variable me ayuda a crear los espacio necesarios del teclado
                val boton = Button(binding.teclado.context)//Variable boton que se va a agregar a la tabla


                if (index >= letrasTeclado.size) {// Sale del ciclo si se ha llegado al final de la lista de letras
                    break
                }


                boton.text = letrasTeclado[index]//Crea el boton con la letra del teclado
                boton.layoutParams = TableRow.LayoutParams(//Parametros de la tabla
                    TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT
                )

                Propiedades(boton)//Propiedades del boton
                tabla.addView(boton)//Agrega el boton a la tabla

                boton.setOnClickListener{//Evento al oprimir el boton
                    val letraIngresada= boton.text.toString().firstOrNull()//Se le asigna el caracter a la letra

                    if(letraIngresada != null){//Aqui verifica que la halla letra
                        if(palabraSecreta.contains(letraIngresada)){//Se verifica si la letra ingresada se encuentra en la palabraSecreta
                            for(i in palabraSecreta.indices){//Recorre la palabraSecreta letra por letra
                                if(palabraSecreta[i] == letraIngresada){//mira si en la posicion [I] se encuentra la letra ingresada
                                    palabraAdivinada[i] = letraIngresada//coloca la letra ingresa en la posicion de la palabra Adivinada
                                }

                            }
                            binding.palabraAdivinadaTextView.text =palabraAdivinada.joinToString(" ")//Muestra la letra ingresada si esta en el textview
                            puntos = puntos + 100;//Aumenta el puntaje

                            if (!palabraAdivinada.contains('_')) {//Verifica si quedan espacios con _ _ _
                                Ganador()//Funcion Ganador
                            }

                        }else{
                            if(vidas==8){
                                val imageResource = resources.getIdentifier("imagen1","drawable",packageName)//Muestra la imagen del ahorcado
                                binding.imagen.setImageResource(imageResource)
                                vidas--//Reduce las vidas
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
                                                            Perdedor()//Si llega a 0 vidas muestra la funcion perdedor
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }

                                }
                            }
                        }
                        boton.isEnabled=false;//Desactiva el boton una vez oprimido
                        boton.setBackgroundResource(R.color.white)//Pinta de blanco el boton desactivado
                    }
                    Informacion()//Actualiza la informacion
                }
            }
            binding.teclado.addView(tabla)//Impime la tabla en la pantalla
        }
    }


    //Funcion que asigna una palabra ramdom
    private fun RandomPalabra(tematica:String):String{
        val frutas= arrayOf("MANZANA", "NARANJA", "PLATANO", "KIWI", "PERA", "FRESA", "MANGO", "SANDIA", "MELON", "UVA")//Arreglo de frutas
        val animales = arrayOf("LEON", "TIGRE", "OSO", "LOBO", "JIRAFA", "ELEFANTE", "RINOCERONTE", "HIPOPOTAMO", "COCODRILO", "SERPIENTE")//Arreglo de Animales
        val ciudades = arrayOf("BOGOTA", "MEDELLIN", "CALI", "BARRANQUILLA", "CARTAGENA", "BUCARAMANGA", "CUCUTA", "IBAGUE", "PASTO", "MANIZALES")//Arreglo de Ciudades
        val deportes = arrayOf("FUTBOL", "BASQUETBOL", "TENIS", "VOLEY", "ATLETISMO", "GIMNASIA", "BOXEO", "NATACION", "CICLISMO", "GOLF")//Arreglo de Deportes
        var seleccionar = (0..9).random()//Numero aleatorio entre 0-9

        if(tematica == "Frutas"){//Si la tematica es Frutas
            return frutas[seleccionar]//Selecciona una Fruta del vector con el numero aleatorio
        }else{
            if(tematica == "Animales"){
                return animales[seleccionar]
            }else{
                if(tematica == "Ciudades"){
                    return ciudades[seleccionar]
                }else{
                    return deportes[seleccionar]
                }
            }
        }

    }


    //Se le dan unas propiedades al boton para el teclado
    private fun Propiedades(boton:Button){
        boton.layoutParams = TableRow.LayoutParams(120, 120)// Tamaño de los botones
        val layoutParams = boton.layoutParams as ViewGroup.MarginLayoutParams
        layoutParams.setMargins(8, 8, 8, 8) // Establece las margenes
        boton.setBackgroundResource(R.color.black)//Color del boton
    }


    //Funcion que vuelve a reiniciar el juego
    fun Reiniciar(){
        val dialogo = AlertDialog.Builder(this)
        val intent = Intent(this, Info1::class.java)//Activity info1

        dialogo.setTitle("Esta seguro de reiniciar el juego presione OK")
        dialogo.setPositiveButton("OK") { dialog, which ->// Este código se ejecutará cuando se presione el botón "OK"
            startActivity(intent)//Inicia la activiti info 1
        }
        val dialog = dialogo.create()
        dialog.show()
    }


    //Funcion que finaliza el juego
    fun Finalizar(){
        val dialogo = AlertDialog.Builder(this)
        val intent = Intent(this, MainActivity::class.java)//Activity Main

        dialogo.setTitle("Si esta seguro de salir presione OK")//Mensaj de la ventana emergente
        dialogo.setPositiveButton("OK") { dialog, which ->// Este código se ejecutará cuando se presione el botón "OK"
            startActivity(intent)//Inicia la activity main
        }
        val dialog = dialogo.create()
        dialog.show()
    }


    //Funcion que me lleva a la activyti ganador
    fun Ganador(){
        var intent = Intent(this, Ganador::class.java)//Muestra la vista del ganador
        intent.putExtra("puntos", puntos)//Envia los puntos para mostrar
        intent.putExtra("palabra", palabraSecreta)//Envia la palabra secreta
        startActivity(intent)//Inicia la actividad ganador
    }


    //Funcion que me lleva a la activyti perdedor
    fun Perdedor(){
        var intent = Intent(this, Perdedor::class.java)//Muestra la vista del perdedor
        intent.putExtra("puntos", puntos)//Envia los puntos para mostrar
        intent.putExtra("palabra", palabraSecreta)//Envia la palabra secreta
        startActivity(intent)//Inicia la actividad perdedor
    }

}