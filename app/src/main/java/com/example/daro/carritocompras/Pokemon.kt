package com.example.daro.carritocompras

import android.os.Parcel
import android.os.Parcelable

class Pokemon(var id: Int, var numeroPokemon: Int, var nombre: String, var poderUno: String, var poderDos: String, var fechaCaptura: String,var nivel: Int,var imagenPokemon:String,var idEntrenador:Int,  var createdAt: Long,
              var updatedAt: Long) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readLong(),
            parcel.readLong()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeInt(numeroPokemon)
        parcel.writeString(nombre)
        parcel.writeString(poderUno)
        parcel.writeString(poderDos)
        parcel.writeString(fechaCaptura)
        parcel.writeInt(nivel)
        parcel.writeString(imagenPokemon)
        parcel.writeInt(idEntrenador)
        parcel.writeLong(createdAt)
        parcel.writeLong(updatedAt)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Pokemon> {
        override fun createFromParcel(parcel: Parcel): Pokemon {
            return Pokemon(parcel)
        }

        override fun newArray(size: Int): Array<Pokemon?> {
            return arrayOfNulls(size)
        }
    }
}
