package com.listview.aktifiti.ui.ui.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.listview.aktifiti.R
import com.listview.aktifiti.databinding.FragmentKegiatanBinding
import com.listview.aktifiti.helper.DataHelper
import com.listview.aktifiti.helper.ViewModelFactory
import com.listview.aktifiti.ui.ui.adapter.KegiatanAdapter
import com.listview.aktifiti.ui.ui.viemodel.KegiatanViewModel

class KegiatanFragment : Fragment() {
    private var _binding : FragmentKegiatanBinding? = null
    private val binding get() = _binding

    private lateinit var adapter : KegiatanAdapter

    private lateinit var kegiatanViewModel: KegiatanViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentKegiatanBinding.inflate(layoutInflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pengguna = KegiatanFragmentArgs.fromBundle(arguments as Bundle).pengguna
        kegiatanViewModel = obtainViewModel(activity as AppCompatActivity)
        kegiatanViewModel.getAllKegiatan(pengguna.id as Int).observe(viewLifecycleOwner){
            Log.d("TAG", "onViewCreated: $it")
            if(it != null){
                adapter.listKegiatan = it
                binding?.rvKegiatan?.adapter = adapter
            }
        }
        binding?.rvKegiatan?.layoutManager = LinearLayoutManager(context)
        binding?.rvKegiatan?.setHasFixedSize(true)
        adapter = KegiatanAdapter(context as Context)


        binding?.tvName?.text = pengguna.nama
        binding?.tvDeks?.text = pengguna.deskripsi
        binding?.tvTime?.text = DataHelper.getCurrentDay()



        binding?.fabAddKegiatan?.setOnClickListener {
            val dialog = AddKegiatanFragment()
            dialog.pengguna = pengguna
            dialog.show(activity?.supportFragmentManager as FragmentManager,"customDialog")
        }

    }

    private fun obtainViewModel(activity : AppCompatActivity): KegiatanViewModel{
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[KegiatanViewModel::class.java]
    }
}