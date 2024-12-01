package com.example.sqlite_reference.models

class Sotuvchi {
    var id :Int? = null
    var name :String? = null
    var number:String? = null

    constructor(id: Int?, name: String?, number: String?) {
        this.id = id
        this.name = name
        this.number = number
    }

    constructor(name: String?, number: String?) {
        this.name = name
        this.number = number
    }

    constructor()

    override fun toString(): String {
        return "Xaridor(id=$id, name=$name, number=$number)"
    }
}