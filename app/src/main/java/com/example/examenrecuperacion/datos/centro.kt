package com.example.examenrecuperacion.datos

import android.os.Parcel
import android.os.Parcelable

data class Centro(val nombre: String, val distancia: String, val imagen: Int) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombre)
        parcel.writeString(distancia)
        parcel.writeInt(imagen)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Centro> {
        override fun createFromParcel(parcel: Parcel): Centro {
            return Centro(parcel)
        }

        override fun newArray(size: Int): Array<Centro?> {
            return arrayOfNulls(size)
        }
    }
}