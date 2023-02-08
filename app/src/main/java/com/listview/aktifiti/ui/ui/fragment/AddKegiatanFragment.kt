package com.listview.aktifiti.ui.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.listview.aktifiti.R
import com.listview.aktifiti.databinding.FragmentAddKegiatanBinding
import com.listview.aktifiti.databinding.FragmentAddPenggunaBinding
import com.listview.aktifiti.databinding.FragmentKegiatanBinding
import com.listview.aktifiti.db.entities.Kegiatan
import com.listview.aktifiti.db.entities.Pengguna
import com.listview.aktifiti.helper.DataHelper
import com.listview.aktifiti.helper.ViewModelFactory
import com.listview.aktifiti.ui.ui.viemodel.KegiatanViewModel
import java.util.*

class AddKegiatanFragment : DialogFragment() {
    private var _binding : FragmentAddKegiatanBinding? = null
    private val binding get() = _binding

    private lateinit var kegiatanViewModel: KegiatanViewModel

    var pengguna : Pengguna? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddKegiatanBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        kegiatanViewModel = obtainViewModel(activity as AppCompatActivity)
        binding?.btnSubmit?.setOnClickListener {
            val namaKegiatan = binding?.edKegiatan?.text.toString().trim()
            when{
                namaKegiatan.isEmpty() ->{
                    binding?.edKegiatan?.error = getString(R.string.empty)
                }
            }
            val kegiatan = Kegiatan(
                null,
                    pengguna?.id,
                    pengguna?.nama.toString(),
                    namaKegiatan,
                    DataHelper.getCurrentDate()
            )
           kegiatanViewModel.insertKegiatan(kegiatan)
           dismiss()
        }
    }

    private fun obtainViewModel(activity : AppCompatActivity): KegiatanViewModel{
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[KegiatanViewModel::class.java]
    }
}