package com.example.daro.carritocompras

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import kotlinx.android.synthetic.main.activity_detalles_entrenador.*

class DetallesEntrenadorClienteActivity : AppCompatActivity() {

    var entrenador: Entrenador? = null
    lateinit var pokemon: ArrayList<Pokemon>
    lateinit var adaptador: PokemonClienteAdapter
    //lateinit var codigoBotonoActivar:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles_entrenador_cliente)

        entrenador = intent.getParcelableExtra("detallesEntrenadorCliente")
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
        adaptador = PokemonClienteAdapter(pokemon)
        recycler_view_pokemon.layoutManager = layoutManager
        recycler_view_pokemon.itemAnimator = DefaultItemAnimator()
        recycler_view_pokemon.adapter = adaptador
        adaptador.notifyDataSetChanged()

        registerForContextMenu(recycler_view_pokemon)
    }
}
