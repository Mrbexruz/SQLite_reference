package com.example.sqlite_reference.models

import java.text.SimpleDateFormat
import java.util.Date

class Buyurtma {
    var id :Int? = null
    var name :String? = null
    var price:Int? = null
    var date:String? = null
    var xaridor :Xaridor? = null
    var sotuvchi : Sotuvchi? = null

    constructor(
        id: Int?,
        name: String?,
        price: Int?,
        date: String?,
        xaridor: Xaridor?,
        sotuvchi: Sotuvchi?
    ) {
        this.id = id
        this.name = name
        this.price = price
        this.date = date
        this.xaridor = xaridor
        this.sotuvchi = sotuvchi
    }

    constructor(name: String?, price: Int?,  xaridor: Xaridor?, sotuvchi: Sotuvchi?) {
        this.name = name
        this.price = price
        this.date = SimpleDateFormat("dd/MM/yyyy HH/MM/ss").format(Date())
        this.xaridor = xaridor
        this.sotuvchi = sotuvchi
    }

    constructor()

    override fun toString(): String {
        return "Buyurtma(id=$id, name=$name, price=$price, date=$date, xaridor=$xaridor, sotuvchi=$sotuvchi)"
    }


}
