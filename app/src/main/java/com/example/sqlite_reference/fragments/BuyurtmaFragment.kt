package com.example.sqlite_reference.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.sqlite_reference.R
import com.example.sqlite_reference.adapter.BuyurtmaAdapter
import com.example.sqlite_reference.databinding.FragmentBuyurtmaBinding
import com.example.sqlite_reference.db.MyDbHelper
import com.example.sqlite_reference.models.Buyurtma
import com.example.sqlite_reference.models.Sotuvchi
import com.example.sqlite_reference.models.Xaridor

class BuyurtmaFragment : Fragment() {
    lateinit var buyurtmaAdapter : BuyurtmaAdapter
    private val binidng by lazy { FragmentBuyurtmaBinding.inflate(layoutInflater) }
    lateinit var myDbHelper: MyDbHelper
    lateinit var xaridorList : List<Xaridor>
    lateinit var sotuvchiList : List<Sotuvchi>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        myDbHelper = MyDbHelper(binidng.root.context)

        showSpinners()

        binidng.apply {
            btnSave.setOnClickListener {
                val buyurtma = Buyurtma(
                    edtName.text.toString(),
                    edtPrice.text.toString().toInt(),
                    xaridorList[spinnerXaridor.selectedItemPosition],
                    sotuvchiList[spinnerSotuvchi.selectedItemPosition]

                )
                myDbHelper.addBuyurtma(buyurtma)
                    Toast.makeText(context, "saqlandi", Toast.LENGTH_SHORT).show()

            }
        }

        val bl  = myDbHelper.showBuyurtmalar()
        buyurtmaAdapter = BuyurtmaAdapter(bl as ArrayList)
        binidng.rv.adapter = buyurtmaAdapter


       return binidng.root
    }

    fun showSpinners(){
         xaridorList = myDbHelper .showXaridorlar()
         sotuvchiList = myDbHelper.showSotuvchilar()

        val  snl = ArrayList<String>()
        sotuvchiList.forEach {
            snl.add(it.name!!)
        }
        val xnl = ArrayList<String>()
        xaridorList.forEach {
            xnl.add(it.name!!)
        }
        val spinnerSotuvchiAdapter = ArrayAdapter<String>(binidng.root.context, android.R.layout.simple_list_item_1,snl)
        binidng.spinnerSotuvchi.adapter = spinnerSotuvchiAdapter

        val spinnerXaridorAdapter = ArrayAdapter<String>(binidng.root.context, android.R.layout.simple_list_item_1,xnl)
        binidng.spinnerXaridor.adapter = spinnerXaridorAdapter

    }

}