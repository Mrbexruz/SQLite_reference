package com.example.sqlite_reference.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.sqlite_reference.R
import com.example.sqlite_reference.adapter.SotuvchiXaridorAdapter
import com.example.sqlite_reference.databinding.FragmentSotuvchiBinding
import com.example.sqlite_reference.db.MyDbHelper
import com.example.sqlite_reference.models.Sotuvchi


class SotuvchiFragment : Fragment() {
    lateinit var myDbHelper: MyDbHelper
    lateinit var sotuvchiXaridorAdapter: SotuvchiXaridorAdapter<Sotuvchi>
    private val binding by lazy { FragmentSotuvchiBinding.inflate(layoutInflater) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding.tvStatus.text = "Sotuvchi"
        myDbHelper = MyDbHelper(binding.root.context)
        sotuvchiXaridorAdapter = SotuvchiXaridorAdapter(myDbHelper.showSotuvchilar())
        binding.rv.adapter = sotuvchiXaridorAdapter

        binding.btnSave.setOnClickListener {
            val sotuvchi = Sotuvchi(
                binding.edtName.text.toString(),
                binding.edtPhone.text.toString()

            )
            myDbHelper.addSotuvchi(sotuvchi)
            Toast.makeText(context, "Saqlandi", Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }

}