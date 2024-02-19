package com.example.proyectodi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.widget.Toolbar
import com.example.proyectodi.databinding.ActivityPerfilBinding
import com.example.proyectodi.databinding.ActivityPrincipalBinding

class PrincipalActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPrincipalBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)

        binding = ActivityPrincipalBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val imageButton: ImageButton = findViewById(R.id.btnprincipal)
        imageButton.setOnClickListener {
            val intent = Intent(this,PrincipalActivity::class.java)
            startActivity(intent)
        }
        imageButton.setImageDrawable(GlobalVariables.usuario!!.getImage()!!.drawable)




    }
}