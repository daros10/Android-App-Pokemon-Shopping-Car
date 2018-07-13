package com.example.daro.carritocompras

import android.os.StrictMode
import android.util.Log
import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost

class DatabasePokemon{
    companion object {

        fun insertarPokemon(pokemon:Pokemon){
            "http://192.168.0.3:1337/Pokemon".httpPost(listOf("numero" to pokemon.numeroPokemon, "nombre" to pokemon.nombre, "poderUno" to pokemon.poderUno, "poderDos" to pokemon.poderDos, "fechaCaptura" to pokemon.fechaCaptura,"nivel" to pokemon.nivel,"entrenadorId" to pokemon.idEntrenador ))
                    .responseString { request, _, result ->
                        Log.d("http-ejemplo", request.toString())
                    }
        }

        fun getPokemonList(entrenadorId: Int): ArrayList<Pokemon> {
            val pokemon: ArrayList<Pokemon> = ArrayList()
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
            val (request, response, result) = "http://192.168.0.3:1337/Pokemon?entrenadorId=$entrenadorId".httpGet().responseString()
            val jsonStringPokemon = result.get()

            val parser = Parser()
            val stringBuilder = StringBuilder(jsonStringPokemon)
            val array = parser.parse(stringBuilder) as JsonArray<JsonObject>

            array.forEach {
                val id = it["id"] as Int
                val numero = it["numero"] as Int
                val nombre = it["nombre"] as String
                val poderUno = it["poderUno"] as String
                val poderDos = it["poderDos"] as String
                val fechaCaptura = it["fechaCaptura"] as String
                val nivel = it["nivel"] as Int
                //val latitud = it["latitud"] as Double
               // val longitud = it["longitud"] as Double
                val pokemoon = Pokemon(id,numero,nombre,poderUno,poderDos,fechaCaptura,nivel,entrenadorId,0,0)
                pokemon.add(pokemoon)
            }
            return pokemon
        }

    }
}