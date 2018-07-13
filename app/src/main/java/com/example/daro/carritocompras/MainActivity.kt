package com.example.daro.carritocompras

import android.content.AsyncQueryHandler
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    lateinit var usuarios: ArrayList<Usuario>
    var estadoIngresoSistema = 0
    lateinit var valorRol:String
    lateinit var usuarioActual:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_login.setOnClickListener { v: View? ->
            irActividadRgistrar()
        }

    }

    fun irActividadRgistrar(){

        val rolUsuario = spinnerRol.selectedItem.toString()
        val nombreUsuario = txtUsername.text.toString()
        val contrasenaUsuario = txtPassword.text.toString()

       //var usuario = Usuario(rolUsuario,nombreUsuario,contrasenaUsuario,0,0)
        //DatabaseUsuario.insertarUsuario(usuario)

        usuarios = DatabaseUsuario.getList()

        for (datos in usuarios){

            if (datos.rol.equals(rolUsuario,true) && datos.nombreUsuario.equals(nombreUsuario,true) && datos.contrasena.equals(contrasenaUsuario,true)){
                estadoIngresoSistema = 1
                valorRol = datos.rol
                usuarioActual = datos.nombreUsuario
            }

        }

        Toast.makeText(this,"VALOR: $estadoIngresoSistema",Toast.LENGTH_SHORT).show()

        if (estadoIngresoSistema==1){
            Toast.makeText(this,"Bienvenido al Sistema: $usuarioActual",Toast.LENGTH_SHORT).show()
            val intent = Intent(this, RegistrarPersonajes::class.java)
            intent.putExtra("valorRol",valorRol)
            startActivity(intent)
        }else{
            Toast.makeText(this,"Datos o Usuario Incorrectos",Toast.LENGTH_SHORT).show()
        }

    }
}



