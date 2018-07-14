package com.example.daro.carritocompras

import android.os.StrictMode
import android.util.Log
import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.github.kittinunf.fuel.*

class DatabaseEntrenador{

    companion object {

        fun insertarEntrenador(entrenador:Entrenador){
            "http://192.168.0.3:1337/Entrenador".httpPost(listOf("nombre" to entrenador.nombre, "apellido" to entrenador.apellido, "fechaNacimiento" to entrenador.fechaNacimiento, "medallas" to entrenador.numeroMedallas, "campeonActual" to entrenador.campeonActual))
                    .responseString { request, _, result ->
                        Log.d("http-ejemplo", request.toString())
                    }
        }

        fun eliminarEntrenador(id: Int) {
            "http://192.168.0.3:1337/Entrenador/$id".httpDelete()
                    .responseString { request, response, result ->
                        Log.d("http-ejemplo", request.toString())
                    }
        }

        fun actualizarEntrenador(entrenador: Entrenador) {
            "http://192.168.0.3:1337/Entrenador/${entrenador.id}".httpPut(listOf("nombre" to entrenador.nombre, "apellido" to entrenador.apellido, "fechaNacimiento" to entrenador.fechaNacimiento, "medallas" to entrenador.numeroMedallas, "campeonActual" to entrenador.campeonActual))
                    .responseString { request, _, result ->
                        Log.d("http-ejemplo", request.toString())
                    }
        }

        fun getList(): ArrayList<Entrenador> {
            val entrenadores: ArrayList<Entrenador> = ArrayList()
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
            val (request, response, result) = "http://192.168.0.3:1337/Entrenador".httpGet().responseString()
            val jsonStringAutor = result.get()

            val parser = Parser()
            val stringBuilder = StringBuilder(jsonStringAutor)
            val array = parser.parse(stringBuilder) as JsonArray<JsonObject>

            array.forEach {
                val id = it["id"] as Int
                val nombre = it["nombre"] as String
                val apellido = it["apellido"] as String
                val fechaNacimiento = it["fechaNacimiento"] as String
                val numeroMedallas = it["medallas"] as Int
                val campeonActual = it["campeonActual"] as Int
                val entrenador = Entrenador(id, nombre, apellido, fechaNacimiento, numeroMedallas, campeonActual, 0, 0)
                entrenadores.add(entrenador)
            }
            return entrenadores
        }

    }
}