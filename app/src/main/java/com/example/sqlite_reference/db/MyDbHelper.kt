package com.example.sqlite_reference.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.sqlite_reference.models.Buyurtma
import com.example.sqlite_reference.models.Sotuvchi
import com.example.sqlite_reference.models.Xaridor

class MyDbHelper(context: Context):SQLiteOpenHelper(context, DB_NAME , null, VERSION), MyDbInterface {
    companion object{
        const val DB_NAME = "market_db"
        const val VERSION = 1
        const val SOTUVCHI_TABLE = "sootuvchi_table"
        const val ID = "id"
        const val NAME = "name"
        const val PHONE = "phone"
        const val XARIDOR_TABLE = "xaridor_table"
        const val BUYURTMA_TABLE = "buyurtma_table"
        const val PRICE = "narx"
        const val DATE = "date"
        const val XARIDOR_ID = "xaridor_id"
        const val SOTUVCHI_ID = "sotuvchi_id"

    }

    override fun onCreate(p0: SQLiteDatabase?) {
        val querySotuvchi = "create table $SOTUVCHI_TABLE($ID integer not null primary key autoincrement unique, $NAME text not null, $PHONE text not null)"
        val queryXaridor = "create table $XARIDOR_TABLE($ID integer not null primary key autoincrement unique, $NAME text not null, $PHONE text not null)"
        val queryBuyurtma= "create table $BUYURTMA_TABLE($ID integer not null primary key autoincrement unique, $NAME text not null, $PRICE integer not null, $DATE text not null, $XARIDOR_ID integer not null, $SOTUVCHI_ID integer not null, FOREIGN KEY ($XARIDOR_ID) REFERENCES $XARIDOR_TABLE($ID), FOREIGN KEY ($SOTUVCHI_ID) REFERENCES $SOTUVCHI_TABLE ($ID))"

        p0?.execSQL(querySotuvchi)
        p0?.execSQL(queryXaridor)
        p0?.execSQL(queryBuyurtma)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    override fun addSotuvchi(sotuvchi: Sotuvchi) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(NAME, sotuvchi.name)
        contentValues.put(PHONE, sotuvchi.number)
        database.insert(SOTUVCHI_TABLE, null, contentValues)
        database.close()
    }

    override fun showSotuvchilar(): List<Sotuvchi> {
       val list = ArrayList<Sotuvchi>()
        val query = "select * from  $SOTUVCHI_TABLE"
        val cursor =  readableDatabase.rawQuery(query, null, null)
        if (cursor.moveToFirst()) {
            do {
                list.add(
                    Sotuvchi(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2)
                )
                )


            } while (cursor.moveToNext())
        }
        return list
    }

    override fun addXaridor(xaridor: Xaridor) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(NAME, xaridor.name)
        contentValues.put(PHONE, xaridor.number)
        database.insert(XARIDOR_TABLE, null, contentValues)
        database.close()
    }

    override fun showXaridorlar(): List<Xaridor> {
        val list = ArrayList<Xaridor>()
        val query = "select * from  $XARIDOR_TABLE"
        val cursor =  readableDatabase.rawQuery(query, null, null)
        if (cursor.moveToFirst()) {
            do {
                list.add(
                    Xaridor(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2)
                    )
                )


            } while (cursor.moveToNext())
        }
        return list
    }

    override fun addBuyurtma(buyurtma: Buyurtma) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(NAME, buyurtma.name)
        contentValues.put(PRICE, buyurtma.price)
        contentValues.put(DATE, buyurtma.date)
        contentValues.put(XARIDOR_ID, buyurtma.xaridor?.id)
        contentValues.put(SOTUVCHI_ID, buyurtma.sotuvchi?.id)
        database.insert(BUYURTMA_TABLE, null, contentValues)
        database.close()
    }

    override fun showBuyurtmalar(): List<Buyurtma> {
        val list = ArrayList<Buyurtma>()
        val query = "select * from  $BUYURTMA_TABLE"
        val cursor =  readableDatabase.rawQuery(query, null, null)
        if (cursor.moveToFirst()) {
            do {
                list.add(Buyurtma (
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getInt(2),
                        cursor.getString(3),
                        getXaridorId(cursor.getInt(4)),
                        getSotuvchiById(cursor.getInt(5))

                    )
                )


            } while (cursor.moveToNext())
        }
        return list
    }

    override fun getSotuvchiById(id: Int): Sotuvchi {
       val database = this.readableDatabase
        val cursor= database.query(SOTUVCHI_TABLE,
        arrayOf(ID, NAME, PHONE),
        "$ID=?",
        arrayOf(id.toString()),
            null, null, null
        )
        cursor?.moveToFirst()
        val sotuvchi = Sotuvchi(
            cursor.getInt(0),
            cursor.getString(1),
            cursor.getString(2)
        )
        return sotuvchi

    }

    override fun getXaridorId(id: Int): Xaridor {
        val database = this.readableDatabase
        val cursor= database.query(
            XARIDOR_TABLE,
            arrayOf(ID, NAME, PHONE),
            "$ID=?",
            arrayOf(id.toString()),
            null, null, null
        )
        cursor?.moveToFirst()
        val xaridor = Xaridor(
            cursor.getInt(0),
            cursor.getString(1),
            cursor.getString(2)
        )
        return xaridor
    }
}