package com.example.appcanciones

import android.content.Context
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CancionAdapter(private val context: Context, private val canciones: List<Cancion>,
                     private val musicPlayer: MusicPlayer) :
    RecyclerView.Adapter<CancionAdapter.CancionViewHolder>() {

    private var mediaPlayer: MediaPlayer? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CancionViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_cancion, parent, false)
        return CancionViewHolder(view)
    }

    override fun onBindViewHolder(holder: CancionViewHolder, position: Int) {
        /**val cancion = canciones[position]
        holder.titulo.text = cancion.titulo


        holder.btnPlay.setOnClickListener {
            mediaPlayer?.release()
            mediaPlayer = MediaPlayer.create(context, cancion.archivo)
            mediaPlayer?.start()
        }

        holder.btnStop.setOnClickListener {
            mediaPlayer?.stop()
            mediaPlayer?.release()
            mediaPlayer = null
        }*/

        val cancion = canciones[position]
        holder.titulo.text = cancion.titulo

        holder.btnPlay.setOnClickListener{
            musicPlayer.playSong(cancion.archivo)
        }

        holder.btnStop.setOnClickListener {
            musicPlayer.stopSong()
        }
    }

    override fun getItemCount(): Int = canciones.size

    class CancionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titulo: TextView = itemView.findViewById(R.id.tvTitulo)
        val btnPlay: Button = itemView.findViewById(R.id.btnPlay)
        val btnStop: Button = itemView.findViewById(R.id.btnStop)

    }
}