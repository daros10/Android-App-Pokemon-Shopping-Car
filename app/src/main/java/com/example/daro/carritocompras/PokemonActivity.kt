package com.example.daro.carritocompras

import android.content.Intent
import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_pokemon.*

class PokemonActivity : AppCompatActivity() {

    var entrenador: Entrenador? = null
    var idEntrenador: Int = 0
    private lateinit var imageBitmap: Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon)

        idEntrenador = intent.getIntExtra("idEntrenador", 0)

        btnGuardarPokemon.setOnClickListener { v: View? ->
            crearPokemon()
        }

        btnTomarFoto.setOnClickListener{v: View? ->
            tomarFoto()
        }
    }

    fun crearPokemon(){
        var numero = txtNumeroPokemon.text.toString().toInt()
        var nombre = txtNombrePokemon.text.toString()
        var poderUno = txtPoderUnoPokemon.text.toString()
        var poderDos = txtPoderDosPokemon.text.toString()
        var fechaCaptura = txtFechaCPokemon.text.toString()
        var nivel = txtNivelPokemon.text.toString().toInt()

        var pokemon = Pokemon(0,numero,nombre,poderUno,poderDos,fechaCaptura,nivel,idEntrenador,0,0)
        DatabasePokemon.insertarPokemon(pokemon)

        finish()

    }

    val REQUEST_IMAGE_CAPTURE = 1


    private fun tomarFoto() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent.resolveActivity(packageManager) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val extras = data.extras
            imageBitmap = extras!!.get("data") as Bitmap
            imageViewPokemon.setImageBitmap(imageBitmap)
        }
    }

}

