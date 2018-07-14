package com.example.daro.carritocompras

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_detalles_entrenador.*
import kotlinx.android.synthetic.main.activity_detalles_pokemon.*
import kotlinx.android.synthetic.main.activity_pokemon.*
import kotlinx.android.synthetic.main.activity_registrar_personajes.*

class DetallesPokemonActivity : AppCompatActivity() {

    var pokemon: Pokemon? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles_pokemon)

        pokemon = intent.getParcelableExtra("detallesPokemon")


        txtShowNumeroPokemon.text = pokemon?.numeroPokemon.toString()
        txtShowNombrePokemon.text = pokemon?.nombre
        txtShowPoderUno.text = pokemon?.poderUno
        txtShowPoderDos.text = pokemon?.poderDos
        txtShowFechaC.text = pokemon?.fechaCaptura
        txtShowNivel.text = pokemon?.nivel.toString()

    }


}
