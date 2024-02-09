package com.example.proyectodi

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat

class PersonalizaActivity : AppCompatActivity() {

    private lateinit var btnGoBack: ImageButton
    private lateinit var btnActualizar: Button
    private lateinit var btnCamera: ImageButton
    private lateinit var btnGallery: ImageButton
    private lateinit var Image: ImageView
    private lateinit var Nombre: EditText
    private lateinit var textViewWarning: TextView
    private lateinit var nombre: String
    //VARIABLE SISTEMA LOG
    private val TAG = "LoginActivity"

        val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()){ uri ->
            if(uri!=null){
                Image.setImageURI(uri)
            }
        }

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_personaliza)

            val email = intent.getStringExtra("Email")
            val password = intent.getStringExtra("Password")

            val user = Usuario() // Revisar

            if(!email.isNullOrEmpty() && !password.isNullOrEmpty()) {
                user.setEmail(email)
                user.setPassword(password)
            }else{

                //Sacar objeto usuario de firebase
            }

            btnGoBack = findViewById(R.id.imageBtnGoBack3)
            btnActualizar = findViewById(R.id.btnActualizar)
            btnCamera = findViewById(R.id.btnCamera)
            btnGallery = findViewById(R.id.btnGallery)
            Image = findViewById<ImageView>(R.id.imageUser)
            Nombre = findViewById<EditText>(R.id.editTextName)
            nombre = Nombre.toString()
            textViewWarning = findViewById(R.id.textViewWarning)


            //CAMERA BUTTON
            btnCamera.setOnClickListener {
                camera()
            }

            //GALLERY BUTTON
            btnGallery.setOnClickListener {
                pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            }

            //GO BACK BUTTON
            btnGoBack.setOnClickListener {
                val intent = Intent(this, SignInActivity::class.java)
                startActivity(intent)
            }

            //ACTUALIZAR BUTTON
            btnActualizar.setOnClickListener {
                if(nombre.isNotEmpty()){
                    user.setImage(Image)
                    user.setNombre(nombre)
                    val intent = Intent(this, PrincipalActivity::class.java)
                    startActivity(intent)
                } else {
                    textViewWarning.text = "Debes rellenar el campo nombre"
                    textViewWarning.visibility = View.VISIBLE
                    Log.d(TAG, "Debes rellenar todos los campos")

                }
            }
        }

        //Funcion Abre la camara
        private fun camera(){
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if(intent.resolveActivity(getPackageManager())!=null){
                startActivityForResult(intent,1)

            }
        }

        //Funcion Captura la imagen
        fun OnActivityResult(requestCode: Int, resultCode: Int, data: Intent){
            super.onActivityResult(requestCode,resultCode,data)
            if(requestCode == 1 && resultCode == RESULT_OK){
                val extras = Bundle(data.extras)
                val imgBitmap : Bitmap = extras.get("data") as Bitmap
                Image.setImageBitmap(imgBitmap)
            }
        }
    }
