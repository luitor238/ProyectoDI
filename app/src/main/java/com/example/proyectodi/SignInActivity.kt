package com.example.proyectodi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import com.example.proyectodi.GlobalVariables.usuario
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignInActivity : AppCompatActivity() {

    private lateinit var Email : EditText
    private lateinit var Password : EditText
    private lateinit var RepeatPassword : EditText
    private lateinit var btnCrearCuenta: Button
    private lateinit var btnVolver: ImageButton
    private lateinit var textViewWarning: TextView
    private lateinit var auth: FirebaseAuth
    //VARIABLE SISTEMA LOG
    private val TAG = "LoginActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)


        Email = findViewById(R.id.editTextEmail)
        Password = findViewById(R.id.editTextPassword)
        RepeatPassword = findViewById(R.id.editTextRepeatPassword)
        btnCrearCuenta = findViewById(R.id.btnCrear)
        btnVolver = findViewById(R.id.imageBtnGoBack)
        textViewWarning = findViewById(R.id.textViewWarning)
        auth= Firebase.auth

        try {
            btnCrearCuenta.setOnClickListener{

                if (Email.text.isNotEmpty() && Password.text.isNotEmpty() && RepeatPassword.text.isNotEmpty()){

                    if(Password.text.toString()==RepeatPassword.text.toString()) {
                        if(Password.length()>=8) {
                            crearUsuario()
                        } else{
                            textViewWarning.text = "La contraseña debe tener minimo 8 caracteres."
                            textViewWarning.visibility = View.VISIBLE
                            Log.d(TAG, "Contraseña muy corta")
                        }
                    }else{
                        textViewWarning.text = "La contraseña no es igual"
                        textViewWarning.visibility = View.VISIBLE
                        Log.d(TAG, "La contraseña no es igual")
                    }

                } else{
                    textViewWarning.text = "Debes rellenar todos los campos"
                    textViewWarning.visibility = View.VISIBLE
                    Log.d(TAG, "Debes rellenar todos los campos")
                }
            }

        } catch (e: Exception) {
            Log.d(TAG, "Error no esperado")
        }

        btnVolver.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        })

    }


    //Funcion CREAR USUARIO
    fun crearUsuario(){
        auth.createUserWithEmailAndPassword(Email.text.toString(), Password.text.toString()).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                Log.d(TAG, "Usuario Creado Correctamente")

                val intent = Intent(this, PersonalizaActivity::class.java)
                intent.putExtra("activity", "ActivitySignIn")
                usuario!!.setEmail(Email.text.toString())
                usuario!!.setPassword(Password.text.toString())
                startActivity(intent)
            } else {
                textViewWarning.text = "Usuario No Creado Correctamente"
                textViewWarning.visibility = View.VISIBLE
                Log.d(TAG, "Usuario No Creado Correctamente")
            }
        }
    }
}