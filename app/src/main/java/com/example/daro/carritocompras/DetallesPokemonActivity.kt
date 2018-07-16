package com.example.daro.carritocompras

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import kotlinx.android.synthetic.main.activity_detalles_pokemon.*


class DetallesPokemonActivity : AppCompatActivity() {

    var pokemon: Pokemon? = null
    lateinit var myBitmapAgain:Bitmap


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

        myBitmapAgain = decodeBase64(pokemon?.imagenPokemon.toString()!!)
        showImageViewPokemon.setImageBitmap(myBitmapAgain)

    }

    fun decodeBase64(input: String): Bitmap {
        val decodedBytes =  Base64.decode(input,0)
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
    }


}
