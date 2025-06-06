package com.example.appcanciones

import android.content.Context
import android.media.MediaPlayer

class MusicPlayer(private val context: Context) {

    private var mediaPlayer: MediaPlayer? = null

    fun playSong(songResource: Int) {
        stopSong() // Detener cualquier canción que esté sonando actualmente
        mediaPlayer = MediaPlayer.create(context, songResource)
        mediaPlayer?.start()
    }

    fun stopSong(){
        mediaPlayer?.stop()
        mediaPlayer = null
    }

    fun cleanup(){
        stopSong()
    }
}