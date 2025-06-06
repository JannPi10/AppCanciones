package com.example.appcanciones

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CancionesActivity : AppCompatActivity() {

    private lateinit var rvCanciones: RecyclerView
    private lateinit var musicPlayer: MusicPlayer
    private lateinit var btnRandom: Button
    private lateinit var btnStopRandom: Button
    private lateinit var adapter: CancionAdapter
    private val listaCanciones = CancionesProvider.listaCanciones
    /** CREE UNA CLASE PROVEEDORA*/
    /**private var mediaPlayer: MediaPlayer? = null*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_canciones)


        //inicializamos las vistas
        btnStopRandom = findViewById(R.id.btnStopRandom)
        rvCanciones = findViewById(R.id.rvCanciones)
        btnRandom = findViewById(R.id.btnRandom)

        //inicializamos el reproductor
        musicPlayer= MusicPlayer(this)

        //esto era lo que tenia antes que no estaba mal pero no es codigo limpio, era mejor en
        //una clase aparte
        /**
        listaCanciones = listOf(
            Cancion("AURORA", R.raw.aurora),
            Cancion("Perdido en tus ojos", R.raw.perdido),
            Cancion("REINA", R.raw.reina),
            Cancion("Otra Vida", R.raw.otravida),
            Cancion("DETRAS DE TU ALMA", R.raw.detras),
            Cancion("EL ULTIMO BESO", R.raw.ultimo)
        )
        */
        //configuramos el recycler view
        adapter = CancionAdapter(this, listaCanciones, musicPlayer)
        rvCanciones.layoutManager = LinearLayoutManager(this)
        rvCanciones.adapter = adapter

        btnRandom.setOnClickListener {
            reproducirCancionPrima()
        }

        btnStopRandom.setOnClickListener {
            musicPlayer.stopSong()
        }
    }


    private fun reproducirCancionPrima() {
        musicPlayer.stopSong() // Detenemos cualquier canción actual
        val index = encontrarPosicionDePrimo(3) // Obtenemos la posición de la 3ra canción prima
        if (index < listaCanciones.size) {
            musicPlayer.playSong(listaCanciones[index].archivo)
        } else {
            Toast.makeText(this, "No hay canción en esa posición", Toast.LENGTH_SHORT).show()
        }
    }


    private fun encontrarPosicionDePrimo(n: Int): Int {
        var contador = 0
        var numero = 2
        while (true) {
            if (esPrimo(numero)) {
                contador++
                if (contador == n) return numero - 1
            }
            numero++
        }
    }

    private fun esPrimo(num: Int): Boolean {
        if (num < 2) return false
        for (i in 2 until num) {
            if (num % i == 0) return false
        }
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        musicPlayer.cleanup() // Limpieza al salir de la actividad
    }
}