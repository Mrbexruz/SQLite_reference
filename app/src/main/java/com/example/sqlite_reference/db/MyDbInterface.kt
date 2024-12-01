package com.example.sqlite_reference.db

import com.example.sqlite_reference.models.Buyurtma
import com.example.sqlite_reference.models.Sotuvchi
import com.example.sqlite_reference.models.Xaridor

interface MyDbInterface {
    fun addSotuvchi(sotuvchi: Sotuvchi)
    fun showSotuvchilar():List<Sotuvchi>

    fun addXaridor(xaridor: Xaridor)
    fun showXaridorlar():List<Xaridor>

    fun addBuyurtma(buyurtma: Buyurtma)
    fun showBuyurtmalar():List<Buyurtma>

    fun getSotuvchiById(id :Int) : Sotuvchi
    fun getXaridorId(id :Int) :Xaridor

}