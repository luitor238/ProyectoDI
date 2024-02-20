package com.example.proyectodi

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import com.example.proyectodi.GlobalVariables.usuario
import com.example.proyectodi.databinding.ActivityPersonalizaBinding

class PersonalizaActivity : AppCompatActivity() {

    private lateinit var Image: ImageView
    private lateinit var binding: ActivityPersonalizaBinding
    val pickMedia =
        registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            if (uri != null) {
                Image.setImageURI(uri)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //INICIALIZA BINDING
        binding = ActivityPersonalizaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //ACTIVITY PRECEDENTE
        val activityOrigin = intent.getStringExtra("activity")

        Image = binding.imageUser

        //FUNCIONALIDAD BOTONES
        binding.btnCamera.setOnClickListener {
            //Abre la camara
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if (intent.resolveActivity(packageManager) != null) {
                startActivityForResult(intent, 1)
            }
        }
        binding.btnGallery.setOnClickListener {
            //Abre la galeria
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }
        binding.imageBtnGoBack.setOnClickListener {
            //Vuelve a la actividad de la que proviene
            when (activityOrigin) {
                "ActivitySignIn" -> {
                    val intent = Intent(this, SignInActivity::class.java)
                    startActivity(intent)
                }
                "ActivityPerfil" -> {
                    val intent = Intent(this, PerfilActivity::class.java)
                    startActivity(intent)
                }
                else -> {

                }
            }
        }
        binding.btnActualizar.setOnClickListener {
            val nombre = binding.editTextName.text.toString()
            if (nombre.isNotEmpty()) {
                usuario!!.setImage(Image)
                usuario!!.setNombre(nombre)
                val intent = Intent(this, PerfilActivity::class.java)
                startActivity(intent)
            } else {
                binding.textViewWarning.text = "Debes rellenar el campo nombre"
                binding.textViewWarning.visibility = View.VISIBLE
            }
        }
    }


    //Funcion Captura la imagen
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == RESULT_OK) {
            val extras = data?.extras
            val imgBitmap: Bitmap = extras?.get("data") as Bitmap
            Image.setImageBitmap(imgBitmap)
        }
    }
}
