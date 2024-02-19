package com.example.proyectodi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.example.proyectodi.GlobalVariables.usuario
import com.example.proyectodi.databinding.ActivityPerfilBinding

class PerfilActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPerfilBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        binding = ActivityPerfilBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val imageButton: ImageButton = findViewById(R.id.btnprincipal)
        imageButton.setOnClickListener {
            val intent = Intent(this,PrincipalActivity::class.java)
            startActivity(intent)
        }
        val imageUser: ImageButton = findViewById(R.id.btnperfil)
        imageUser.setImageDrawable(usuario!!.getImage()!!.drawable)


        binding.imageUser.setImageDrawable(usuario!!.getImage()!!.drawable)
        binding.textViewEmail.text = usuario!!.getEmail()
        binding.textViewNombre.text = usuario!!.getNombre()
        binding.textViewPassword.text = usuario!!.getPassword()

        binding.btnEditar.setOnClickListener {
            val intent = Intent(this,PersonalizaActivity::class.java)
            startActivity(intent)
        }

        binding.btnSalir.setOnClickListener {
            binding.viewHabilidad.visibility = View.VISIBLE
            binding.textViewPregunta.visibility = View.VISIBLE
            binding.btnSi.visibility = View.VISIBLE
            binding.btnNo.visibility = View.VISIBLE
        }

        binding.btnSi.setOnClickListener {
            val dbHelper = DatabaseHelper(this)
            dbHelper.insertarUsuario(usuario!!)
            finishAffinity()
        }

        binding.btnNo.setOnClickListener {
            binding.viewHabilidad.visibility = View.INVISIBLE
            binding.textViewPregunta.visibility = View.INVISIBLE
            binding.btnSi.visibility = View.INVISIBLE
            binding.btnNo.visibility = View.INVISIBLE
        }
    }
}