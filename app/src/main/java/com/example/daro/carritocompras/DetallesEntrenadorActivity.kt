package com.example.daro.carritocompras

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_detalles_entrenador.*

class DetallesEntrenadorActivity : AppCompatActivity() {

    var entrenador: Entrenador? = null
    lateinit var pokemon: ArrayList<Pokemon>
    lateinit var adaptador: PokemonAdapter
    //lateinit var codigoBotonoActivar:String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles_entrenador)



        entrenador = intent.getParcelableExtra("detallesEntrenador")
        //codigoBotonoActivar = intent.getStringExtra("codigoBotonoActivar")

        //Toast.makeText(this,codigoBotonoActivar,Toast.LENGTH_SHORT).show()

        txtShowIdEntrenador.text = entrenador?.id.toString()
        txtShowNombreEntrenador.text = entrenador?.nombre
        txtShowApellidoEntrenador.text = entrenador?.apellido
        txtShowFechaNEntrenador.text = entrenador?.fechaNacimiento
        txtShowNMedallasEntrenador.text = entrenador?.numeroMedallas.toString()
        txtShowCampeonEntrenador.text = if(entrenador?.campeonActual == 1) "Si" else "No"



        pokemon = DatabasePokemon.getPokemonList(entrenador?.id!!)
       Log.d("resultado",pokemon.toString())

       val layoutManager = LinearLayoutManager(this)
        adaptador = PokemonAdapter(pokemon)
        recycler_view_pokemon.layoutManager = layoutManager
        recycler_view_pokemon.itemAnimator = DefaultItemAnimator()
        recycler_view_pokemon.adapter = adaptador
        adaptador.notifyDataSetChanged()

        registerForContextMenu(recycler_view_pokemon)

        btnNuevoPokemon.setOnClickListener { v: View? ->
            irActividdadCrearPokemon()
        }
    }

    fun irActividdadCrearPokemon(){
        val intent = Intent(this, PokemonActivity::class.java)
        intent.putExtra("tipo", "Create")
        intent.putExtra("idEntrenador", entrenador?.id!!)
        startActivity(intent)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        var position = adaptador.getPosition()
        var pokemonn = pokemon[position]

        when (item.itemId) {
        /*R.id.item_menu_compartir -> {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/html"
            intent.putExtra(Intent.EXTRA_SUBJECT, "${getString(R.string.autor)} - ${getString(R.string.app_name)}")
            intent.putExtra(Intent.EXTRA_TEXT, "${getString(R.string.name)} ${autor.nombre} ${autor.apellido}\n${getString(R.string.numero_libros)} ${autor.numeroMedallas}\n${getString(R.string.fecha_nacimiento)} ${autor.fechaNacimiento}")
            startActivity(intent)
            return true
        }*/
            R.id.item_menu_editar -> {
                val intent = Intent(this, PokemonActivity::class.java)
                intent.putExtra("tipo", "Edit")
                intent.putExtra("pokemon", pokemonn)
                startActivity(intent)
                return true
            }
            R.id.item_menu_eliminar -> {
                val builder = AlertDialog.Builder(this)
                builder.setMessage("Esta seguro de eliminar?")
                        .setPositiveButton("Si", { dialog, which ->
                            DatabasePokemon.eliminarPokemon(pokemonn.id)
                            finish()
                            startActivity(intent)
                        }
                        )
                        .setNegativeButton("No", null)
                val dialogo = builder.create()
                dialogo.show()
                return true
            }
            else -> {
                Log.i("menu", "Todos los demas")
                return super.onOptionsItemSelected(item)
            }
        }
    }

}
