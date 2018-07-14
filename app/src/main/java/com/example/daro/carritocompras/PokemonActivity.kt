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
import android.R.attr.data
import android.app.Activity
import android.net.Uri
import android.os.Environment
import android.support.v4.content.FileProvider
import kotlinx.android.synthetic.main.activity_detalles_pokemon.*
import kotlinx.android.synthetic.main.activity_entrenador.*
import java.io.File
import java.text.SimpleDateFormat
import java.util.*


class PokemonActivity : AppCompatActivity() {

    var pokemon: Pokemon? = null
    var idEntrenador: Int = 0
    private lateinit var imageBitmap: Bitmap
    var tipo = false


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

        if (!tipo){
            var pokemon = Pokemon(0,numero,nombre,poderUno,poderDos,fechaCaptura,nivel,idEntrenador,0,0)
            DatabasePokemon.insertarPokemon(pokemon)

        }else{
            var pokemon = Pokemon(pokemon?.id!!,numero,nombre,poderUno,poderDos,fechaCaptura,nivel,idEntrenador,0,0)
            DatabasePokemon.actualizarPokemon(pokemon)
        }

        finish()

    }

    val REQUEST_IMAGE_CAPTURE = 1

    private fun tomarFoto() {
        /*val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent.resolveActivity(packageManager) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        }*/

        val archivoImagen = crearArchivo(
                "JPEG_",
                Environment.DIRECTORY_PICTURES,
                ".jpg")

        enviarIntentFoto(archivoImagen)
    }

    private fun crearArchivo(prefijo: String, directorio: String, extension: String): File {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())

        val imageFileName = prefijo + timeStamp + "_"
        val storageDir = getExternalFilesDir(directorio)

        return File.createTempFile(imageFileName, /* prefix */extension, /* suffix */storageDir      /* directory */
        )
    }

    private fun enviarIntentFoto(archivo: File) {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        val photoURI: Uri = FileProvider.getUriForFile(this, "com.example.daro.carritocompras.fileprovider", archivo)

        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)

        if (takePictureIntent.resolveActivity(packageManager) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        }

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
       /* super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val extras = data.extras
            imageBitmap = extras!!.get("data") as Bitmap
            imageViewPokemon.setImageBitmap(imageBitmap)
        }*/
        when (requestCode) {
            REQUEST_IMAGE_CAPTURE-> {
                if (resultCode == Activity.RESULT_OK) {
                    val extras = data.extras
                    val fotoActualBitmap = extras!!.get("data") as Bitmap
                    imageViewPokemon.setImageBitmap(fotoActualBitmap)

                }
            }
        }

    }



}

