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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CancionViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_cancion, parent, false)
        return CancionViewHolder(view)
    }

    override fun onBindViewHolder(holder: CancionViewHolder, position: Int) {
        val cancion = canciones[position]
        holder.bind(cancion)
    }

    override fun getItemCount(): Int = canciones.size

    inner class CancionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titulo: TextView = itemView.findViewById(R.id.tvTitulo)
        private val btnPlay: Button = itemView.findViewById(R.id.btnPlay)
        private val btnStop: Button = itemView.findViewById(R.id.btnStop)


        fun bind(cancion: Cancion){

            titulo.text = cancion.titulo

            btnPlay.setOnClickListener{
                musicPlayer.playSong(cancion.archivo)
            }

            btnStop.setOnClickListener {
                musicPlayer.stopSong()
            }
        }

    }
}