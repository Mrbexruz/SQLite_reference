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
import com.example.sqlite_reference.models.Xaridor


class XaridorFragment : Fragment() {
    lateinit var myDbHelper: MyDbHelper
    lateinit var sotuvchiXaridorAdapter: SotuvchiXaridorAdapter<Xaridor>
    private val binding by lazy { FragmentSotuvchiBinding.inflate(layoutInflater) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding.tvStatus.text = "Xaridor"
        myDbHelper = MyDbHelper(binding.root.context)
        sotuvchiXaridorAdapter = SotuvchiXaridorAdapter(myDbHelper.showXaridorlar())
        binding.rv.adapter = sotuvchiXaridorAdapter

        binding.btnSave.setOnClickListener {
            val xaridor = Xaridor(
                binding.edtName.text.toString(),
                binding.edtPhone.text.toString()
            )
            myDbHelper.addXaridor(xaridor)
            Toast.makeText(context, "Saqlandi", Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }

}