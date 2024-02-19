package com.example.proyectodi


import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.widget.ImageView
import androidx.core.graphics.drawable.toBitmap
import java.io.ByteArrayOutputStream

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE = "BBDD.db"

        // Constantes de la tabla USUARIOS
        private const val TABLA_USUARIOS = "usuarios"
        private const val KEY_ID_USUARIO = "id"
        private const val COLUMN_EMAIL = "email"
        private const val COLUMN_NOMBRE = "nombre"
        private const val COLUMN_PASSWORD = "password"
        private const val COLUMN_IMAGEN = "imagen"

        // Constantes de la tabla PELICULAS
        private const val TABLA_PELICULAS = "peliculas"
        private const val KEY_ID = "id"
        private const val COLUMN_PORTADA = "portada"
        private const val COLUMN_TITULO = "nombre"
        private const val COLUMN_SINOPSIS = "sinopsis"
    }

    override fun onCreate(db: SQLiteDatabase) {

        val USUARIOS = "CREATE TABLE $TABLA_USUARIOS(" +
                "$KEY_ID_USUARIO TEXT PRIMARY KEY AUTOINCREMENT," +
                "$COLUMN_EMAIL TEXT," +
                "$COLUMN_NOMBRE TEXT," +
                "$COLUMN_PASSWORD TEXT," +
                "$COLUMN_IMAGEN TEXT)"
        db.execSQL(USUARIOS)

        val PELICULAS = "CREATE TABLE $TABLA_PELICULAS(" +
                "$KEY_ID TEXT PRIMARY KEY AUTOINCREMENT," +
                "$COLUMN_PORTADA TEXT," +
                "$COLUMN_TITULO TEXT," +
                "$COLUMN_SINOPSIS TEXT)"
        db.execSQL(PELICULAS)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLA_USUARIOS")
        db.execSQL("DROP TABLE IF EXISTS $TABLA_PELICULAS")
        onCreate(db)
    }

    fun insertarUsuario(usuario: Usuario) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(KEY_ID_USUARIO, usuario.getId())
            put(COLUMN_EMAIL, usuario.getEmail())
            put(COLUMN_NOMBRE, usuario.getNombre())
            put(COLUMN_PASSWORD, usuario.getPassword())
            put(COLUMN_IMAGEN, imageViewToString(usuario.getImage()!!))
        }
        db.insert(TABLA_USUARIOS, null, values)
        db.close()
    }

    fun insertarPelicula(pelicula: Pelicula) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_PORTADA, pelicula.getPortada())
            put(COLUMN_TITULO, pelicula.getNombre())
            put(COLUMN_SINOPSIS, pelicula.getSinopsis())
        }
        db.insert(TABLA_PELICULAS, null, values)
        db.close()
    }

    @SuppressLint("Range")
    fun getUsuarios(): ArrayList<Usuario> {
        val usuarios = ArrayList<Usuario>()
        val selectQuery = "SELECT * FROM $TABLA_PELICULAS"
        val db = readableDatabase
        val cursor = db.rawQuery(selectQuery, null)
        cursor.use {
            if (it.moveToFirst()) {
                do {
                    val id = it.getString(it.getColumnIndex(KEY_ID_USUARIO))
                    val email = it.getString(it.getColumnIndex(COLUMN_EMAIL))
                    val nombre = it.getString(it.getColumnIndex(COLUMN_NOMBRE))
                    val password = it.getString(it.getColumnIndex(COLUMN_PASSWORD))
                    val imagen = it.getString(it.getColumnIndex(COLUMN_IMAGEN))
                    var image: ImageView? = null
                    stringToImageView(imagen, image!!)
                    val usuario = Usuario(id, email, nombre, password, image)

                    usuarios.add(usuario)
                } while (it.moveToNext())
            }
        }
        return usuarios
    }

    @SuppressLint("Range")
    fun getPelicula(): ArrayList<Pelicula> {
        val articulos = ArrayList<Pelicula>()
        val selectQuery = "SELECT * FROM $TABLA_PELICULAS"
        val db = readableDatabase
        val cursor = db.rawQuery(selectQuery, null)
        cursor.use {
            if (it.moveToFirst()) {
                do {
                    val id = it.getInt(it.getColumnIndex(KEY_ID))
                    val nombre = it.getString(it.getColumnIndex(COLUMN_TITULO))
                    val portada = it.getString(it.getColumnIndex(COLUMN_PORTADA))
                    val sinopsis = it.getString(it.getColumnIndex(COLUMN_SINOPSIS))
                    val pelicula = Pelicula(nombre, portada, sinopsis)

                    articulos.add(pelicula)
                } while (it.moveToNext())
            }
        }
        return articulos
    }
    
    fun eliminarRegistro(id: Int): Boolean {
        val db = writableDatabase
        val whereClause = "$KEY_ID = ?"
        val whereArgs = arrayOf(id.toString())
        return db.delete(TABLA_PELICULAS, whereClause, whereArgs) > 0
    }

    fun imageViewToString(imageView: ImageView): String {
        val bitmap = (imageView.drawable).toBitmap()
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
        val byteArray = byteArrayOutputStream.toByteArray()
        return Base64.encodeToString(byteArray, Base64.DEFAULT)
    }

    fun stringToImageView(encodedString: String, imageView: ImageView) {
        val decodedString = Base64.decode(encodedString, Base64.DEFAULT)
        val decodedBitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
        imageView.setImageBitmap(decodedBitmap)
    }
}

