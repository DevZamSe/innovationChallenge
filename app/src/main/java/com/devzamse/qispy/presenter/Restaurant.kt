package com.devzamse.qispy.presenter

class Restaurant(nombre: String, foto: Int, aforo: String){
    var nombre = ""
    var aforo = ""
    var foto = 0

    init {
        this.nombre = nombre
        this.foto = foto
        this.aforo = aforo
    }
}