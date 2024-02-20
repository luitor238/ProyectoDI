package com.example.proyectodi

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter

//ADAPTER PARA LAS PELICULAS
class ImagePagerAdapter(private val context: Context) : PagerAdapter() {
    //Lista de portadas
    private val images = intArrayOf(
        R.drawable.portada_1,
        R.drawable.portada_2,
        R.drawable.portada_3,
        R.drawable.portada_5,
        R.drawable.portada_6,
        R.drawable.portada_7,
        R.drawable.portada_8,
        R.drawable.portada_9,
        R.drawable.portada_10,
    )
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val imageView = ImageView(context)
        imageView.setImageResource(images[position])
        imageView.setOnClickListener {
            val intent = Intent(context, InfoActivity::class.java)
            context.startActivity(intent)
        }
        container.addView(imageView)
        return imageView
    }
    override fun getCount(): Int {
        return images.size
    }
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}

//ADAPTER PARA LAS SERIES
class ImagePagerAdapter2(private val context: Context) : PagerAdapter() {
    //Lista de portadas
    private val images = intArrayOf(
        R.drawable.portada_a,
        R.drawable.portada_b,
        R.drawable.portada_c,
        R.drawable.portada_d,
        R.drawable.portada_e,
        R.drawable.portada_f,
        R.drawable.portada_g,
        R.drawable.portada_h,
        R.drawable.portada_i,
    )
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val imageView = ImageView(context)
        imageView.setImageResource(images[position])
        imageView.setOnClickListener {
            val intent = Intent(context, InfoActivity::class.java)
            context.startActivity(intent)
        }
        container.addView(imageView)
        return imageView
    }
    override fun getCount(): Int {
        return images.size
    }
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}
