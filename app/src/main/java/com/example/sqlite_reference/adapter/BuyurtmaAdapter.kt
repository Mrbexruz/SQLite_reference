package com.example.sqlite_reference.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sqlite_reference.databinding.ItemBuyurtmaBinding
import com.example.sqlite_reference.models.Buyurtma

class BuyurtmaAdapter( var list: ArrayList<Buyurtma>) : RecyclerView.Adapter<BuyurtmaAdapter.Vh>()  {
    inner class Vh(val itemRv: ItemBuyurtmaBinding) : RecyclerView.ViewHolder(itemRv.root){
        fun onBind(buyurtma : Buyurtma){
            itemRv.tvName.text = buyurtma.name
            itemRv.tvId.text = buyurtma.id.toString()
            itemRv.tvPrice.text = buyurtma.price.toString()
            itemRv.tvDate.text = buyurtma.date
            itemRv.tvXaridor.text = buyurtma.xaridor?.name
            itemRv.tvSotuvchi.text = buyurtma.sotuvchi?.name

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemBuyurtmaBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }
}