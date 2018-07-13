package com.example.daro.carritocompras

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_detalles_entrenador.*
import kotlin.reflect.jvm.internal.impl.renderer.ClassifierNamePolicy

class DetallesEntrenadorActivity : AppCompatActivity() {

    var entrenador: Entrenador? = null
    lateinit var pokemon: ArrayList<Pokemon>
    lateinit var adaptador: PokemonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles_entrenador)

        entrenador = intent.getParcelableExtra("detallesEntrenador")

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
        intent.putExtra("idEntrenador", entrenador?.id!!)
        startActivity(intent)
    }
}
