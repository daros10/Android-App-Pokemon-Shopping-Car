package com.example.daro.carritocompras

import android.content.Intent
import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_pokemon.*
import java.io.ByteArrayOutputStream
import android.graphics.BitmapFactory
import android.util.Base64


class PokemonActivity : AppCompatActivity() {

    var pokemon: Pokemon? = null
    var idEntrenador: Int = 0
    private lateinit var imageBitmap: Bitmap
    var tipo = false
    lateinit var myBase64Image:String
    lateinit var myBitmapAgain:Bitmap


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon)

        idEntrenador = intent.getIntExtra("idEntrenador", 0)

        val type = intent.getStringExtra("tipo")

        if (type.equals("Edit")) {
            textViewPokemon.text = "Editar Pokemon"
            pokemon = intent.getParcelableExtra("pokemon")
            fillFields()
            tipo = true
        }

        btnGuardarPokemon.setOnClickListener { v: View? ->
            crearPokemon()
        }

        btnTomarFoto.setOnClickListener{v: View? ->
            tomarFoto()

        }
    }

    fun fillFields() {
        txtNumeroPokemon.setText(pokemon?.numeroPokemon.toString())
        txtNombrePokemon.setText(pokemon?.nombre)
        txtPoderUnoPokemon.setText(pokemon?.poderUno)
        txtPoderDosPokemon.setText(pokemon?.poderDos)
        txtFechaCPokemon.setText(pokemon?.fechaCaptura)
        txtNivelPokemon.setText(pokemon?.nivel.toString())
    }

    fun crearPokemon(){
        var numero = txtNumeroPokemon.text.toString().toInt()
        var nombre = txtNombrePokemon.text.toString()
        var poderUno = txtPoderUnoPokemon.text.toString()
        var poderDos = txtPoderDosPokemon.text.toString()
        var fechaCaptura = txtFechaCPokemon.text.toString()
        var nivel = txtNivelPokemon.text.toString().toInt()
        var imagenPokemon = myBase64Image

        if (!tipo){
            var pokemon = Pokemon(0,numero,nombre,poderUno,poderDos,fechaCaptura,nivel,imagenPokemon,idEntrenador,0,0)
            DatabasePokemon.insertarPokemon(pokemon)
            //Toast.makeText(this,"SAVEDDD: $imagenPokemon", Toast.LENGTH_SHORT).show()

        }else{
            var pokemon = Pokemon(pokemon?.id!!,numero,nombre,poderUno,poderDos,fechaCaptura,nivel,imagenPokemon,idEntrenador,0,0)
            DatabasePokemon.actualizarPokemon(pokemon)
        }

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
       super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val extras = data.extras
            imageBitmap = extras!!.get("data") as Bitmap

            myBase64Image = encodeToBase64(imageBitmap, Bitmap.CompressFormat.JPEG, 100)
            myBitmapAgain = decodeBase64(myBase64Image)

            imageViewPokemon.setImageBitmap(myBitmapAgain)

            //Toast.makeText(this,"Dentro de camara: $myBase64Image", Toast.LENGTH_SHORT).show()

        }

    }

    fun encodeToBase64(image: Bitmap, compressFormat: Bitmap.CompressFormat, quality: Int): String {
        val byteArrayOS = ByteArrayOutputStream()
        image.compress(compressFormat, quality, byteArrayOS)
        return android.util.Base64.encodeToString(byteArrayOS.toByteArray(), android.util.Base64.DEFAULT)

    }

    fun decodeBase64(input: String): Bitmap {
        val decodedBytes =  Base64.decode(input,0)
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
    }



}

