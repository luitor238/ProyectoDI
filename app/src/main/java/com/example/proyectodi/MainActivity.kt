package com.example.proyectodi


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.proyectodi.GlobalVariables.usuario
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {

    //VARIABLES
    private lateinit var Email : EditText
    private lateinit var Password : EditText
    private lateinit var btnIniciarSesion: Button
    private lateinit var btnCrearCuenta: Button
    private lateinit var textViewWarning: TextView
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {

        //CREACION DE LA VISTA
        auth = com.google.firebase.Firebase.auth

        //LLAMADA BASE DE DATOS
        val dbHelper = DatabaseHelper(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //ASIGNACION DE VARIABLES
        Email = findViewById(R.id.editTextEmail)
        Password = findViewById(R.id.editTextPassword)
        btnIniciarSesion = findViewById(R.id.btnLogin)
        btnCrearCuenta = findViewById(R.id.btnSignIn)
        textViewWarning = findViewById(R.id.textViewWarning)
        usuario= Usuario("","","","",null)

        //FUNCIONALIDAD BOTONES
        try {
            btnIniciarSesion.setOnClickListener{

                if (Email.text.isNotEmpty() && Password.text.isNotEmpty()) {
                    auth.signInWithEmailAndPassword(Email.text.toString(),Password.text.toString()).addOnCompleteListener(this) { task ->

                        if (task.isSuccessful) {

                            val userId = FirebaseAuth.getInstance().currentUser?.uid
                            if (userId != null) {

                                val usuarios = dbHelper.getUsuarios()

                                if (usuarios.isNotEmpty()) {
                                    for (e in usuarios) {
                                        if (e.getId() == userId) {
                                            usuario!!.setId(e.getId())
                                            usuario!!.setEmail(e.getEmail())
                                            usuario!!.setPassword(e.getPassword())
                                            usuario!!.setNombre(e.getNombre())
                                            usuario!!.setImage(e.getImage()!!)
                                        }
                                    }
                                }

                                val intent = Intent(this, PrincipalActivity::class.java)
                                startActivity(intent)

                            } else {
                                android.app.AlertDialog.Builder(this)
                                    .setMessage("Email o contraseña incorreectos")
                                    .setPositiveButton("Aceptar", null)
                                    .create()
                                    .show()
                            }
                            } else {
                                android.app.AlertDialog.Builder(this)
                                    .setMessage("Email o contraseña incorreectos")
                                    .setPositiveButton("Aceptar", null)
                                    .create()
                                    .show()
                            }
                        }
                }else{
                    textViewWarning.text = "Debes rellenar todos los campos"
                    textViewWarning.visibility = View.VISIBLE
                }
            }
        } catch (e: Exception) {
            android.app.AlertDialog.Builder(this)
                .setMessage("Error en la autenticación")
                .setPositiveButton("Aceptar", null)
                .create()
                .show()
        }

        btnCrearCuenta.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        })

    }
}
