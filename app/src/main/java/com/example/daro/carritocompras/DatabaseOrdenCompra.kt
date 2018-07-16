package com.example.daro.carritocompras

import android.os.StrictMode
import android.util.Log
import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost

class DatabaseOrdenCompra{
    companion object {
        fun insertarOrden(ordenCompra: OrdenCompra){
            "http://192.168.0.3:1337/Orden".httpPost(listOf("cedulaIdentidad" to ordenCompra.cedulaComprador, "sector" to ordenCompra.sector, "idPokemon" to ordenCompra.idPokemon))
                    .responseString { request, _, result ->
                        Log.d("http-ejemplo", request.toString())
                    }
        }

        fun insertarOrdenDetalles(ordenDetalles: OrdenDetalles){
            "http://192.168.0.3:1337/DetalleOrden".httpPost(listOf("fechaEnvio" to ordenDetalles.fechaEnvio, "precio" to ordenDetalles.precio, "idPokemon" to ordenDetalles.idPokemon))
                    .responseString { request, _, result ->
                        Log.d("http-ejemplo", request.toString())
                    }
        }



        fun getOrdenesList(): ArrayList<OrdenCompra> {
            val orden: ArrayList<OrdenCompra> = ArrayList()
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
            val (request, response, result) = "http://192.168.0.3:1337/Orden".httpGet().responseString()
            val jsonStringPokemon = result.get()

            val parser = Parser()
            val stringBuilder = StringBuilder(jsonStringPokemon)
            val array = parser.parse(stringBuilder) as JsonArray<JsonObject>

            array.forEach {
                val cedulaIdentidad = it["cedulaIdentidad"] as Int
                val sector = it["sector"] as String
                val idPokemon = it["idPokemon"] as Int
                //val latitud = it["latitud"] as Double
                // val longitud = it["longitud"] as Double
                val ordenn = OrdenCompra(0,cedulaIdentidad,sector,idPokemon)
                orden.add(ordenn)
            }
            return orden
        }
    }



}