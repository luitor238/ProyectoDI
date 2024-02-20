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
import com.google.firebase.auth.FirebaseAuth

class PerfilActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPerfilBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        //INICIALIZA BINDING
        binding = ActivityPerfilBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //ACTIVITY PRECEDENTE
        val activityOrigin = intent.getStringExtra("activity")

        //FUNCIONALIDAD TOOLBAR
        toolbar()

        //ASIGNACION DE ELEMENTOS segun usuario
        binding.imageUser.setImageDrawable(usuario!!.getImage()!!.drawable)
        binding.textViewEmail.text = usuario!!.getEmail()
        binding.textViewNombre.text = usuario!!.getNombre()
        binding.textViewPassword.text = usuario!!.getPassword()

        //Cambia el nombre del boton volver si venimos de crearnos un nuevo usuario
        if (activityOrigin == "ActivitySignIn") {
            binding.btnVolver.text = "PRINCIPAL"
        }

        //FUNCIONALIDAD BOTONES
        binding.btnEditar.setOnClickListener {
            val intent = Intent(this,PersonalizaActivity::class.java)
            intent.putExtra("activity", "ActivityPerfil")
            startActivity(intent)
        }
        binding.btnVolver.setOnClickListener {
            val intent = Intent(this,PrincipalActivity::class.java)
            startActivity(intent)
        }
        binding.btnSalir.setOnClickListener {
            binding.viewHabilidad.visibility = View.VISIBLE
            binding.textViewPregunta.visibility = View.VISIBLE
            binding.btnSi.visibility = View.VISIBLE
            binding.btnNo.visibility = View.VISIBLE
        }
        binding.btnSi.setOnClickListener {
            val userId = FirebaseAuth.getInstance().currentUser?.uid
            usuario!!.setId(userId!!)
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
        imageUser.setImageDrawable(usuario!!.getImage()!!.drawable)
        imageUser.setOnClickListener {
            val intent = Intent(this,PerfilActivity::class.java)
            startActivity(intent)
        }
    }
}