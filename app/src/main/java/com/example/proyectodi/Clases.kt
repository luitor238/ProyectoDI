package com.example.proyectodi

import android.widget.ImageView
import java.io.Serializable

class Pelicula (private var nombre: String, private var portada: String, private var sinopsis: String){
    fun getNombre(): String{
        return nombre
    }
    fun getPortada(): String{
        return portada
    }
    fun getSinopsis(): String{
        return sinopsis
    }
}


class Usuario(
    private var id: String,
        private var email: String,
        private var password: String,
        private var nombre: String,
        private var image: ImageView) : Serializable {

    fun getId(): String {
        return id
    }
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


object GlobalVariables {
    var pelicula: Pelicula? = null
    var usuario: Usuario? = null
}