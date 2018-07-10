package com.example.daro.carritocompras

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_login.setOnClickListener { v: View? ->
            irActividadRgistrar()
        }
    }

    fun irActividadRgistrar(){


        val username : String = "user"
        val password : String = "user"

        if (username.equals(txtUsername.text.toString(),true) && password.equals(txtPassword.text.toString(),true)){
            Toast.makeText(this,"Bienvenido",Toast.LENGTH_SHORT).show()
            val intent = Intent(this, RegistrarPersonajes::class.java)
            startActivity(intent)
        }else{
            Toast.makeText(this,"Username o Password incorrectos",Toast.LENGTH_SHORT).show()
        }

    }
}
