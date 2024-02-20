package com.example.proyectodi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.widget.Toolbar
import com.example.proyectodi.databinding.ActivityPerfilBinding

class InfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        //INICIALIZA BINDING
        var binding = ActivityPerfilBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //FUNCIONALIDAD TOOLBAR
        toolbar()


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