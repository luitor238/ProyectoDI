package com.example.proyectodi

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyectodi.databinding.ActivityPerfilBinding
import com.example.proyectodi.databinding.ActivityPrincipalBinding

class PrincipalActivity : AppCompatActivity() {


    private lateinit var adapter: CustomAdapter02

    private lateinit var binding: ActivityPrincipalBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)

        val dbHelper = DatabaseHelper(this)


        val imageView: ImageView? = null
        imageView!!.setImageResource(R.drawable.portada_1)
        var pelicula = Pelicula("nombre", imageView, "Fast & Furious es una película de acción trepidante que sigue la historia de Dominic Toretto, un talentoso corredor de carreras callejeras que lidera un equipo de expertos en automovilismo. Cuando un policía encubierto, Brian O'Conner, se infiltra en su mundo para investigar una serie de robos de equipos electrónicos, se desarrolla una intensa rivalidad entre los dos hombres. A medida que las carreras y las confrontaciones aumentan en intensidad, ambos se ven obligados a enfrentarse a sus propios demonios y a tomar decisiones difíciles que pondrán a prueba sus lealtades y su determinación.")
        dbHelper.insertarPelicula(pelicula)

        imageView!!.setImageResource(R.drawable.portada_2)
        pelicula = Pelicula("nombre", imageView, "Fast & Furious es una película de acción trepidante que sigue la historia de Dominic Toretto, un talentoso corredor de carreras callejeras que lidera un equipo de expertos en automovilismo. Cuando un policía encubierto, Brian O'Conner, se infiltra en su mundo para investigar una serie de robos de equipos electrónicos, se desarrolla una intensa rivalidad entre los dos hombres. A medida que las carreras y las confrontaciones aumentan en intensidad, ambos se ven obligados a enfrentarse a sus propios demonios y a tomar decisiones difíciles que pondrán a prueba sus lealtades y su determinación.")
        dbHelper.insertarPelicula(pelicula)

        imageView!!.setImageResource(R.drawable.portada_3)
        pelicula = Pelicula("nombre", imageView, "Fast & Furious es una película de acción trepidante que sigue la historia de Dominic Toretto, un talentoso corredor de carreras callejeras que lidera un equipo de expertos en automovilismo. Cuando un policía encubierto, Brian O'Conner, se infiltra en su mundo para investigar una serie de robos de equipos electrónicos, se desarrolla una intensa rivalidad entre los dos hombres. A medida que las carreras y las confrontaciones aumentan en intensidad, ambos se ven obligados a enfrentarse a sus propios demonios y a tomar decisiones difíciles que pondrán a prueba sus lealtades y su determinación.")
        dbHelper.insertarPelicula(pelicula)

        imageView!!.setImageResource(R.drawable.portada_5)
        pelicula = Pelicula("nombre", imageView, "Fast & Furious es una película de acción trepidante que sigue la historia de Dominic Toretto, un talentoso corredor de carreras callejeras que lidera un equipo de expertos en automovilismo. Cuando un policía encubierto, Brian O'Conner, se infiltra en su mundo para investigar una serie de robos de equipos electrónicos, se desarrolla una intensa rivalidad entre los dos hombres. A medida que las carreras y las confrontaciones aumentan en intensidad, ambos se ven obligados a enfrentarse a sus propios demonios y a tomar decisiones difíciles que pondrán a prueba sus lealtades y su determinación.")
        dbHelper.insertarPelicula(pelicula)

        imageView!!.setImageResource(R.drawable.portada_6)
        pelicula = Pelicula("nombre", imageView, "Fast & Furious es una película de acción trepidante que sigue la historia de Dominic Toretto, un talentoso corredor de carreras callejeras que lidera un equipo de expertos en automovilismo. Cuando un policía encubierto, Brian O'Conner, se infiltra en su mundo para investigar una serie de robos de equipos electrónicos, se desarrolla una intensa rivalidad entre los dos hombres. A medida que las carreras y las confrontaciones aumentan en intensidad, ambos se ven obligados a enfrentarse a sus propios demonios y a tomar decisiones difíciles que pondrán a prueba sus lealtades y su determinación.")
        dbHelper.insertarPelicula(pelicula)

        imageView!!.setImageResource(R.drawable.portada_7)
        pelicula = Pelicula("nombre", imageView, "Fast & Furious es una película de acción trepidante que sigue la historia de Dominic Toretto, un talentoso corredor de carreras callejeras que lidera un equipo de expertos en automovilismo. Cuando un policía encubierto, Brian O'Conner, se infiltra en su mundo para investigar una serie de robos de equipos electrónicos, se desarrolla una intensa rivalidad entre los dos hombres. A medida que las carreras y las confrontaciones aumentan en intensidad, ambos se ven obligados a enfrentarse a sus propios demonios y a tomar decisiones difíciles que pondrán a prueba sus lealtades y su determinación.")
        dbHelper.insertarPelicula(pelicula)

        imageView!!.setImageResource(R.drawable.portada_8)
        pelicula = Pelicula("nombre", imageView, "Fast & Furious es una película de acción trepidante que sigue la historia de Dominic Toretto, un talentoso corredor de carreras callejeras que lidera un equipo de expertos en automovilismo. Cuando un policía encubierto, Brian O'Conner, se infiltra en su mundo para investigar una serie de robos de equipos electrónicos, se desarrolla una intensa rivalidad entre los dos hombres. A medida que las carreras y las confrontaciones aumentan en intensidad, ambos se ven obligados a enfrentarse a sus propios demonios y a tomar decisiones difíciles que pondrán a prueba sus lealtades y su determinación.")
        dbHelper.insertarPelicula(pelicula)

        imageView!!.setImageResource(R.drawable.portada_9)
        pelicula = Pelicula("nombre", imageView, "Fast & Furious es una película de acción trepidante que sigue la historia de Dominic Toretto, un talentoso corredor de carreras callejeras que lidera un equipo de expertos en automovilismo. Cuando un policía encubierto, Brian O'Conner, se infiltra en su mundo para investigar una serie de robos de equipos electrónicos, se desarrolla una intensa rivalidad entre los dos hombres. A medida que las carreras y las confrontaciones aumentan en intensidad, ambos se ven obligados a enfrentarse a sus propios demonios y a tomar decisiones difíciles que pondrán a prueba sus lealtades y su determinación.")
        dbHelper.insertarPelicula(pelicula)

        imageView!!.setImageResource(R.drawable.portada_10)
        pelicula = Pelicula("nombre", imageView, "Fast & Furious es una película de acción trepidante que sigue la historia de Dominic Toretto, un talentoso corredor de carreras callejeras que lidera un equipo de expertos en automovilismo. Cuando un policía encubierto, Brian O'Conner, se infiltra en su mundo para investigar una serie de robos de equipos electrónicos, se desarrolla una intensa rivalidad entre los dos hombres. A medida que las carreras y las confrontaciones aumentan en intensidad, ambos se ven obligados a enfrentarse a sus propios demonios y a tomar decisiones difíciles que pondrán a prueba sus lealtades y su determinación.")
        dbHelper.insertarPelicula(pelicula)


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

        val imageUser: ImageButton = findViewById(R.id.btnperfil)
        if (GlobalVariables.usuario != null) {
            imageUser.setImageDrawable(GlobalVariables.usuario!!.getImage()!!.drawable)}
        imageUser.setOnClickListener {
            val intent = Intent(this,PerfilActivity::class.java)
            startActivity(intent)
        }


        val lista = dbHelper.getPelicula()


        Log.d(ContentValues.TAG, "Crea una instancia de tu adaptador y configúralo en el RecyclerView principal")

        binding.recyclerView2.layoutManager = LinearLayoutManager(this)

        adapter = CustomAdapter02(this, lista)
        binding.recyclerView2.setAdapter(adapter)






    }
}