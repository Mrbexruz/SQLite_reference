package com.example.sqlite_reference.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sqlite_reference.databinding.ItemRvBinding
import com.example.sqlite_reference.models.Sotuvchi
import com.example.sqlite_reference.models.Xaridor

class SotuvchiXaridorAdapter<T>(var list: List<T>) : RecyclerView.Adapter<SotuvchiXaridorAdapter<T>.Vh>() {
    inner class Vh( val itemRv: ItemRvBinding) : RecyclerView.ViewHolder(itemRv.root){

        fun onBind (sx : T){
            try {
                val sotuvchi : Sotuvchi = sx as Sotuvchi
                bindSotuvchi(sotuvchi)

            }catch (e : Exception) {
                val xaridor: Xaridor = sx as Xaridor
                bindXaridor(xaridor)

            }
        }
        private fun bindSotuvchi(sotuvchi: Sotuvchi){

            itemRv.tvId.text = sotuvchi.id.toString()
            itemRv.tvName.text = sotuvchi.name
            itemRv.tvNumber.text = sotuvchi.number

        }
        private fun bindXaridor(xaridor: Xaridor){
            itemRv.tvId.text = xaridor.id.toString()
            itemRv.tvName.text = xaridor.name
            itemRv.tvNumber.text = xaridor.number
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
       return list.size
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }
}