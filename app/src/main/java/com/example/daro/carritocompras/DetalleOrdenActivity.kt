package com.example.daro.carritocompras

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.tapadoo.alerter.Alerter
import kotlinx.android.synthetic.main.activity_detalle_orden.*


class DetalleOrdenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        var oredenes: OrdenCompra? = null


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_orden)

        oredenes = intent.getParcelableExtra("detallesOrden")

        txtShowCedula.text = oredenes?.cedulaComprador.toString()
        txtShowSector.text = oredenes?.sector
        txtShowIdPokemon.text = oredenes?.idPokemon.toString()

        btnGuardarDatosOrden.setOnClickListener { v: View? ->
            guardarDatosOrdenDetalles()

        }

    }

    fun guardarDatosOrdenDetalles(){
        val fechaEnvio = txtFechaEnvio.text.toString()
        val costoPokemon = txtPrecioPokemon.text.toString().toInt()
        val idPokemon = txtShowIdPokemon.text.toString().toInt()
        val ordenDetalles = OrdenDetalles(0,fechaEnvio,costoPokemon,idPokemon)
        DatabaseOrdenCompra.insertarOrdenDetalles(ordenDetalles)
        Alerter.create(this)
                .setTitle("Orden enviada a CLIENTE")
                .setText("La solicitud fue enviada exitosamente")
                .enableSwipeToDismiss()
                .show()
    }
}
