package com.example.daro.carritocompras

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

class PokemonAdapter(private val pokemonList: List<Pokemon>) :  RecyclerView.Adapter<PokemonAdapter.MyViewHolder>(){

    private var position: Int = 0

    fun getPosition(): Int {
        return position
    }

    fun setPosition(position: Int) {
        this.position = position
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnCreateContextMenuListener {

        var nombre: TextView
        var poderUno : TextView
        var poderDos: TextView
        var detalles: Button

        lateinit var pokemoOn: Pokemon

        init {
            nombre = view.findViewById(R.id.txtNombreEntrenador) as TextView
            poderUno = view.findViewById(R.id.txtApellidoEntrenador) as TextView
            poderDos = view.findViewById(R.id.txtFechaNacimientoEntrenador) as TextView
            detalles = view.findViewById(R.id.btnDetallesEntrenado) as Button
            view.setOnCreateContextMenuListener(this)
        }

        override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
            /*menu?.add(Menu.NONE, R.id.item_menu_compartir, Menu.NONE, R.string.menu_share)
             menu?.add(Menu.NONE, R.id.item_menu_editar, Menu.NONE, R.string.menu_edit)
             menu?.add(Menu.NONE, R.id.item_menu_borrar, Menu.NONE, R.string.menu_delete)*/
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_entrenador_layout, parent, false)

        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val pokemonN = pokemonList[position]
        holder.nombre.text = pokemonN.nombre
        holder.poderUno.text = pokemonN.poderUno
        holder.poderDos.text = pokemonN.poderDos
        holder.pokemoOn = pokemonN
        /*holder.detalles.setOnClickListener{
            v: View ->
            val intent = Intent(v.context, DetallesEntrenadorActivity::class.java)
            intent.putExtra("detallesEntrenador", pokemonN)
            v.context.startActivity(intent)
        }*/
        holder.itemView.setOnLongClickListener {
            setPosition(holder.adapterPosition)
            false
        }
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }


}