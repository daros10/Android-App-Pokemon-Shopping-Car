package com.example.daro.carritocompras
import android.os.StrictMode
import android.util.Log

import com.beust.klaxon.*
import com.github.kittinunf.fuel.*

class DatabaseUsuario{
    companion object {

        fun insertarUsuario(usuario: Usuario) {
            "http://192.168.0.3:1337/Usuario".httpPost(listOf("rol" to usuario.rol, "username" to usuario.nombreUsuario, "password" to usuario.contrasena))
                    .responseString { request, _, result ->
                        Log.d("http-ejemplo", request.toString())
                    }
        }

        fun getList(): ArrayList<Usuario> {
            val usuarios: ArrayList<Usuario> = ArrayList()
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
            val (request, response, result) = "http://192.168.0.3:1337/Usuario".httpGet().responseString()
            val jsonStringAutor = result.get()

            val parser = Parser()
            val stringBuilder = StringBuilder(jsonStringAutor)
            val array = parser.parse(stringBuilder) as JsonArray<JsonObject>

            array.forEach {
                val id = it["id"] as Int
                val rol = it["rol"] as String
                val nombreUsuario = it["username"] as String
                val password = it["password"] as String
                val usuario = Usuario(rol,nombreUsuario,password, 0, 0)
                usuarios.add(usuario)
            }
            return usuarios
        }
    }


}
        /*val DB_NAME = "carritoCompras"
        val TABLE_NAME = "usuario"
        val CAMPO_ROL_USUARIO = "rol"
        val CAMPO_NOMBRE_USUARIO = "nombreUsuario"
        val CAMPO_CONTRASENA_USUARIO = "contrasenaUsuario"
        val CAMPO_ID = "id"
    }

    class DBUsuarioHandlerAplicacion(context: Context) : SQLiteOpenHelper(context, DatabaseUsuario.DB_NAME, null, 1) {

        override fun onCreate(db: SQLiteDatabase?) {

            val createTableSQL = "CREATE TABLE ${DatabaseUsuario.TABLE_NAME} (${DatabaseUsuario.CAMPO_ID} INTEGER PRIMARY KEY AUTOINCREMENT, ${DatabaseUsuario.CAMPO_ROL_USUARIO} VARCHAR(50),${DatabaseUsuario.CAMPO_NOMBRE_USUARIO} VARCHAR(50),${DatabaseUsuario.CAMPO_CONTRASENA_USUARIO} VARCHAR(20))"
            db?.execSQL(createTableSQL)
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }


        fun insertarUsuario(usuario: Usuario) {
            val dbWriteable = writableDatabase
            val cv = ContentValues()

            cv.put(DatabaseUsuario.CAMPO_ID, usuario.idUsuario)
            cv.put(DatabaseUsuario.CAMPO_ROL_USUARIO, usuario.rol)
            cv.put(DatabaseUsuario.CAMPO_NOMBRE_USUARIO, usuario.nombreUsuario)
            cv.put(DatabaseUsuario.CAMPO_CONTRASENA_USUARIO, usuario.contrasena)

            val resultado = dbWriteable.insert(DatabaseUsuario.TABLE_NAME, null, cv)

            Log.i("database", "Si es -1 hubo error, sino exito: Respuesta: $resultado")

            dbWriteable.close()
        }

        fun usuarioActual(nombreUsuario:String, contrasena:String):Boolean{
            val db=writableDatabase
            val query="SELECT * FROM usuario WHERE nombreUsuario=$nombreUsuario and contrasenaUsuario=$contrasena"
            val cursor=db.rawQuery(query,null)
            if(cursor.count<=0){
                cursor.close()
                return false
            }
            cursor.close()
            return true
        }


    }
}*/