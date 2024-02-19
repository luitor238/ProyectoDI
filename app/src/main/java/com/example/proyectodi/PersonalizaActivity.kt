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
import com.example.proyectodi.GlobalVariables.usuario
import com.example.proyectodi.databinding.ActivityPerfilBinding
import com.example.proyectodi.databinding.ActivityPersonalizaBinding

class PersonalizaActivity : AppCompatActivity() {

    private lateinit var Image: ImageView
    private lateinit var binding: ActivityPersonalizaBinding
    private val TAG = "LoginActivity"


    val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()){ uri ->
        if(uri!=null){
            Image.setImageURI(uri)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personaliza)

        binding = ActivityPersonalizaBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        Image = findViewById<ImageView>(R.id.imageUser)
        val nombre = binding.editTextName.toString()


        //CAMERA BUTTON
        binding.btnCamera.setOnClickListener {
            camera()
        }

        //GALLERY BUTTON
        binding.btnGallery.setOnClickListener {
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }

        //GO BACK BUTTON
        binding.imageBtnGoBack3.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }

        //ACTUALIZAR BUTTON
        binding.btnActualizar.setOnClickListener {
            if(nombre.isNotEmpty()){
                usuario!!.setImage(Image)
                usuario!!.setNombre(nombre)
                val intent = Intent(this, PerfilActivity::class.java)
                startActivity(intent)
            } else {
                binding.textViewWarning.text = "Debes rellenar el campo nombre"
                binding.textViewWarning.visibility = View.VISIBLE
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
