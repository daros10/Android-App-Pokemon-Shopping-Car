package com.example.daro.carritocompras

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.tapadoo.alerter.Alerter
import kotlinx.android.synthetic.main.activity_datos_comprador.*
import android.content.Intent



class DatosCompradorActivity : AppCompatActivity() {

    lateinit var idPokemonn:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_datos_comprador)

       idPokemonn = intent.getStringExtra("idPokemon")

        //Toast.makeText(this,idPokemonn,Toast.LENGTH_SHORT).show()

        btnEnviarDatosComprador.setOnClickListener { v: View? ->
            crearOreden()
        }
    }

    fun crearOreden(){
        var cedula = txtCedulaComprador.text.toString().toInt()
        var sector = txtSector.text.toString()
        var idPokemon = idPokemonn.toString().toInt()

        var oredenCompra = OrdenCompra(0,cedula,sector,idPokemon)
        DatabaseOrdenCompra.insertarOrden(oredenCompra)

        Alerter.create(this)
                .setTitle("Datos Enviados a DELIVERY")
                .setText("Su peticion ha sido enviada satisfactoriamente")
                .setDuration(10000)
                .enableSwipeToDismiss()
                .setOnClickListener(View.OnClickListener {
                    irAbuscarEntrenador()
                }).show()


    }

    fun irAbuscarEntrenador(){
        txtCedulaComprador.setText("")
        txtSector.setText("")
        val intent = Intent(this,BuscarEntrenadorActivity::class.java)
        startActivity(intent)
    }
}
