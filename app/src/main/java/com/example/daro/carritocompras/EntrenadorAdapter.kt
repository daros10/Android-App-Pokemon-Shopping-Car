package com.example.daro.carritocompras

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.Button
import android.widget.TextView

class EntrenadorAdapter(private val entrenadorList: List<Entrenador>) :  RecyclerView.Adapter<EntrenadorAdapter.MyViewHolder>(){

    private var position: Int = 0

    fun getPosition(): Int {
        return position
    }

    fun setPosition(position: Int) {
        this.position = position
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnCreateContextMenuListener {

        var nombre: TextView
        var apellido : TextView
        var fechaNacimiento: TextView
        var detalles: Button

        lateinit var entrenador: Entrenador

        init {
            nombre = view.findViewById(R.id.txtNombreEntrenador) as TextView
            apellido = view.findViewById(R.id.txtApellidoEntrenador) as TextView
            fechaNacimiento = view.findViewById(R.id.txtFechaNacimientoEntrenador) as TextView
            detalles = view.findViewById(R.id.btnDetallesEntrenado) as Button
            view.setOnCreateContextMenuListener(this)
        }

        override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
           /*menu?.add(Menu.NONE, R.id.item_menu_compartir, Menu.NONE, R.string.menu_share)*/
            menu?.add(Menu.NONE, R.id.item_menu_editar, Menu.NONE, "Editar")
            menu?.add(Menu.NONE, R.id.item_menu_eliminar, Menu.NONE, "Eliminar")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_entrenador_layout, parent, false)

        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val entrenador = entrenadorList[position]
        holder.nombre.text = entrenador.nombre
        holder.apellido.text = entrenador.apellido
        holder.fechaNacimiento.text = entrenador.fechaNacimiento
        holder.entrenador = entrenador
        holder.detalles.setOnClickListener{
            v: View ->
            val intent = Intent(v.context, DetallesEntrenadorActivity::class.java)
            intent.putExtra("detallesEntrenador", entrenador)
            v.context.startActivity(intent)
        }
        holder.itemView.setOnLongClickListener {
            setPosition(holder.adapterPosition)
            false
        }
    }

    override fun getItemCount(): Int {
        return entrenadorList.size
    }


}