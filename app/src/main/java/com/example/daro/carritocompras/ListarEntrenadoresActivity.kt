package com.example.daro.carritocompras

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_listar_entrenadores.*

class ListarEntrenadoresActivity : AppCompatActivity() {

    lateinit var adaptador: EntrenadorAdapter
    lateinit var entrenadores: ArrayList<Entrenador>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listar_entrenadores)

        entrenadores = DatabaseEntrenador.getList()


        val layoutManager = LinearLayoutManager(this)
        adaptador = EntrenadorAdapter(entrenadores)
        recyclerViewEntrenador.layoutManager = layoutManager
        recyclerViewEntrenador.itemAnimator = DefaultItemAnimator()
        recyclerViewEntrenador.adapter = adaptador
        adaptador.notifyDataSetChanged()

        registerForContextMenu(recyclerViewEntrenador)
    }
}
