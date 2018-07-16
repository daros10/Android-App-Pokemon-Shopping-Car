package com.example.daro.carritocompras

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_detalles_pokemon.*
import kotlinx.android.synthetic.main.activity_detalles_pokemon_cliente.*

class DetallesPokemonClienteActivity : AppCompatActivity() {

    var pokemon: Pokemon? = null
    lateinit var myBitmapAgain: Bitmap
    lateinit var idPokemon:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles_pokemon_cliente)

        pokemon = intent.getParcelableExtra("detallesPokemonCliente")



        txtShowNumeroPokemonn.text = pokemon?.numeroPokemon.toString()
        txtShowNombrePokemonn.text = pokemon?.nombre
        txtShowPoderUnoo.text = pokemon?.poderUno
        txtShowPoderDoss.text = pokemon?.poderDos
        txtShowFechaCC.text = pokemon?.fechaCaptura
        txtShowNivell.text = pokemon?.nivel.toString()

        //Toast.makeText(this,pokemon?.id.toString(),Toast.LENGTH_SHORT).show()


        myBitmapAgain = decodeBase64(pokemon?.imagenPokemon.toString()!!)
        showImageViewPokemonn.setImageBitmap(myBitmapAgain)

        btnAdquirirPokemon.setOnClickListener { v ->
            irActividadDatosComprador()
        }


    }

    fun decodeBase64(input: String): Bitmap {
        val decodedBytes =  Base64.decode(input,0)
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
    }

    fun irActividadDatosComprador(){
        val intent = Intent(this, DatosCompradorActivity::class.java)
        idPokemon = pokemon?.id.toString()
        intent.putExtra("idPokemon",idPokemon)
        startActivity(intent)
    }


}
