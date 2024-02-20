package com.example.proyectodi

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.example.proyectodi.databinding.ActivityPerfilBinding
import com.example.proyectodi.databinding.ActivityPrincipalBinding

class PrincipalActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPrincipalBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)

        //INICIALIZA BINDING
        binding = ActivityPrincipalBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //FUNCIONALIDAD TOOLBAR
        toolbar()

        //FUNCIONALIDAD BOTONES
        binding.btnSeries.setOnClickListener{
            binding.btnSeries.background.setTint(ContextCompat.getColor(this, R.color.secondary))
            binding.btnPeliculas.background.setTint(ContextCompat.getColor(this, R.color.terciary))
            binding.viewPager.visibility = View.VISIBLE
            binding.viewPagerPeliculas.visibility = View.GONE
        }
        binding.btnPeliculas.setOnClickListener{
            binding.btnPeliculas.background.setTint(ContextCompat.getColor(this, R.color.secondary))
            binding.btnSeries.background.setTint(ContextCompat.getColor(this, R.color.terciary))
            binding.viewPager.visibility = View.GONE
            binding.viewPagerPeliculas.visibility = View.VISIBLE
        }

        //CARRUSEL DE PELICULAS
        var viewPager = findViewById<ViewPager>(R.id.viewPager)
        var adapter = ImagePagerAdapter(this)
        viewPager.adapter = adapter

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {}

            override fun onPageSelected(position: Int) {

            }
        })

        //CARRUSEL DE SERIES
        viewPager = findViewById<ViewPager>(R.id.viewPagerPeliculas)
        var adapter2 = ImagePagerAdapter2(this)
        viewPager.adapter = adapter2

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {}

            override fun onPageSelected(position: Int) {

            }
        })
    }

    //FUNCION PARA LA FUNCIONALIDAD DEL TOOLBAR
    fun toolbar(){
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val imageButton: ImageButton = findViewById(R.id.btnprincipal)
        imageButton.setOnClickListener {
            val intent = Intent(this,PrincipalActivity::class.java)
            startActivity(intent)
        }
        val imageUser: ImageButton = findViewById(R.id.btnperfil)
        imageUser.setImageDrawable(GlobalVariables.usuario!!.getImage()!!.drawable)
        imageUser.setOnClickListener {
            val intent = Intent(this,PerfilActivity::class.java)
            startActivity(intent)
        }
    }
}