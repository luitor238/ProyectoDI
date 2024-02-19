package com.example.proyectodi


import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Adapter(private val context: Context, private val images: List<Int>) :
    RecyclerView.Adapter<Adapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        //INFLAMOS LO QUE SERA EL ELEMENTO DEL RECYCLEVIEW
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.list_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //ASIGNACION DE LOS RECURSOS DE LA LISTA
        val currentImage = images[position]
        holder.imageView1.setImageResource(currentImage)
        holder.imageView2.setImageResource(currentImage)
        holder.imageView3.setImageResource(currentImage)

        val slideInBottom = AnimationUtils.loadAnimation(context, R.anim.slide_in_bottom)
        val slideOutTop = AnimationUtils.loadAnimation(context, R.anim.slide_out_top)

        val animationDuration = 2000L // Duración de la animación en milisegundos

        // Función para ejecutar las animaciones
        fun runAnimation() {
            for (i in 1..5) {
                holder.imageView1.startAnimation(slideInBottom)
                holder.imageView2.startAnimation(slideInBottom)
                holder.imageView3.startAnimation(slideInBottom)

                slideInBottom.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation?) {}

                    override fun onAnimationEnd(animation: Animation?) {
                        holder.imageView1.startAnimation(slideOutTop)
                        holder.imageView2.startAnimation(slideOutTop)
                        holder.imageView3.startAnimation(slideOutTop)
                    }

                    override fun onAnimationRepeat(animation: Animation?) {}
                })
            }
        }

        // Ejecutar la animación inicial y luego repetirla continuamente
        runAnimation()
        Log.d("TAG", "Error?")

    }


    override fun getItemCount(): Int {
        return images.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView1: ImageView = itemView.findViewById(R.id.imageView)
        var imageView2: ImageView = itemView.findViewById(R.id.imageView2)
        var imageView3: ImageView = itemView.findViewById(R.id.imageView3)
    }
}

class CustomAdapter02(private val context: Context, private val lista: ArrayList<Pelicula>) :
    RecyclerView.Adapter<CustomAdapter02.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item2, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pelicula = lista[position]
        holder.texto.text = pelicula.getNombre()
        holder.portada.setImageDrawable(pelicula.getPortada().drawable)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, InfoActivity::class.java)
            intent.putExtra("pelicula", pelicula)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var portada: ImageView = itemView.findViewById(R.id.posters)
        var texto: TextView = itemView.findViewById(R.id.textViewTitulo)
    }
}



class HorizontalAdapter(private val context: Context, private val itemList: List<List<Any>>) : RecyclerView.Adapter<HorizontalAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Inflamos el diseño del elemento del RecyclerView horizontal
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item3, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        if (item.isNotEmpty() && item[0] is Int) {
            holder.imageView1.setImageResource(item[0] as Int)
        }
        holder.imageView1.setOnClickListener {
            val intent = Intent(context, InfoActivity::class.java).apply {
                // Acceder a los elementos en itemList
                val PrimerElemento = item[0] as? Int ?: 0
                val SegundoElemento = item[1] as? Int ?: 0
                val TercerElemento = item[2] as? String ?: ""
                val CuartoElemento = item[3] as? String ?: ""
                val QuintoElemento = item[4] as? String ?: ""
                val SextoElemento = item[5] as? String ?: ""

                // Pasar datos de la película a la Activity de detalles
                putExtra("MOVIE_FHOTO", PrimerElemento)
                putExtra("MOVIE_FHOTO2", SegundoElemento)
                putExtra("MOVIE_NAME", TercerElemento)
                putExtra("MOVIE_RATE", CuartoElemento)
                putExtra("MOVIE_RESUMEN", QuintoElemento)
                putExtra("MOVIE_URL", SextoElemento)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView1: ImageView = itemView.findViewById(R.id.posters)
    }
}
