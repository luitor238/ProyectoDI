package com.example.proyectodi

import android.widget.ImageView
import java.io.Serializable

class Usuario() : Serializable{

    private var email: String = ""
    private var password: String = ""
    private var nombre: String = ""
    private var image: ImageView? = null


    fun getEmail(): String {
        return email
    }
    fun setEmail(newEmail: String) {
        email = newEmail
    }
    fun getPassword(): String {
        return password
    }
    fun setPassword(newPassword: String) {
        password = newPassword
    }
    fun getNombre(): String {
        return nombre
    }
    fun setNombre(newUsername: String) {
        nombre = newUsername
    }
    fun getImage(): ImageView? {
        return image
    }
    fun setImage(newImage: ImageView) {
        image = newImage
    }

}