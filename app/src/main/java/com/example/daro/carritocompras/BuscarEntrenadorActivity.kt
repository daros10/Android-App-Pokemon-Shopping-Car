package com.example.daro.carritocompras

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_buscar_entrenador.*

class BuscarEntrenadorActivity : AppCompatActivity() {

    lateinit var adaptador: EntrenadorClienteAdapter
    lateinit var entrenadores: ArrayList<Entrenador>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buscar_entrenador)


        btnBuscarEntrenadorServer.setOnClickListener { v: View? ->
            consultarDatos()
        }


    }

    fun consultarDatos(){
        if (txtBuscarEntrenador.equals("")){
            Toast.makeText(this,"Ingrese parametro de busqueda",Toast.LENGTH_SHORT).show()
        }else{
            var datoBusqueda:String = txtBuscarEntrenador.text.toString()
            

            entrenadores = DatabaseEntrenador.buscarEntrenador(datoBusqueda)

            //Toast.makeText(this,datoBusqueda,Toast.LENGTH_SHORT).show()

            val layoutManager = LinearLayoutManager(this)
            adaptador = EntrenadorClienteAdapter(entrenadores)
            recyclerView_Resultados_Entrenador.layoutManager = layoutManager
            recyclerView_Resultados_Entrenador.itemAnimator = DefaultItemAnimator()
            recyclerView_Resultados_Entrenador.adapter = adaptador
            adaptador.notifyDataSetChanged()

            registerForContextMenu(recyclerView_Resultados_Entrenador)

        }
    }
}
