package com.example.daro.carritocompras

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_entrenador.*

class EntrenadorActivity : AppCompatActivity() {

    var entrenador: Entrenador? = null
    var tipo = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entrenador)

        val type = intent.getStringExtra("tipo")

        if (type.equals("Edit")) {
            textViewEntreador.text = "Editar Entrenador"
            entrenador = intent.getParcelableExtra("entrenador")
            fillFields()
            tipo = true
        }

        btnGuardarEntrenador.setOnClickListener { v: View? ->
            crearEntrenador()
        }
    }

    fun fillFields() {
        txtNombreEntrenador.setText(entrenador?.nombre)
        txtApellidoEntrenador.setText(entrenador?.apellido)
        txtFechaNacimientoEntrenador.setText(entrenador?.fechaNacimiento)
        txtNumeroMedallasEntrenador.setText(entrenador?.numeroMedallas.toString())
        if (entrenador?.campeonActual == 1) {
            switchCampeonEntrenador.toggle()
        }
    }

    fun crearEntrenador(){
        var nombre = txtNombreEntrenador.text.toString()
        var apellido = txtApellidoEntrenador.text.toString()
        var fecha = txtFechaNacimientoEntrenador.text.toString()
        var numeroMedallas = txtNumeroMedallasEntrenador.text.toString().toInt()
        var campeon = if (switchCampeonEntrenador.isChecked) 1 else 0


        if (!tipo){

            var entrenador = Entrenador(0, nombre, apellido, fecha, numeroMedallas, campeon,0,0)
            DatabaseEntrenador.insertarEntrenador(entrenador)

        }else{
            var entrenador = Entrenador(entrenador?.id!!, nombre, apellido, fecha, numeroMedallas, campeon,0,0)
            DatabaseEntrenador.actualizarEntrenador(entrenador)
        }

        //Log.d("print",DatabaseEntrenador.getList().toString())
        //print(DatabaseEntrenador.getList())
        irListarEntrenadorActivity()

    }

    fun irListarEntrenadorActivity(){
        val intent = Intent(this, ListarEntrenadoresActivity::class.java)
        startActivity(intent)
    }
}
