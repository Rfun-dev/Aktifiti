package com.listview.aktifiti.ui.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.listview.aktifiti.R
import com.listview.aktifiti.databinding.FragmentAddPenggunaBinding
import com.listview.aktifiti.db.entities.Pengguna
import com.listview.aktifiti.helper.ViewModelFactory
import com.listview.aktifiti.ui.ui.viemodel.PenggunaViewModel

class AddPenggunaFragment : DialogFragment() {
    private var _binding : FragmentAddPenggunaBinding? = null
    private val binding get() = _binding
    var pengguna : Pengguna? = null

    private lateinit var penggunaViewModel: PenggunaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddPenggunaBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(pengguna != null){
            binding?.edNama?.setText(pengguna?.nama)
            binding?.edDesk?.setText(pengguna?.deskripsi)
        }
        penggunaViewModel = obtainViewModel(activity as AppCompatActivity)
        binding?.btnSubmit?.setOnClickListener {
            val nama = binding?.edNama?.text.toString().trim()
            val deskripsi = binding?.edDesk?.text.toString().trim()
            when{
                nama.isEmpty() ->{
                    binding?.edNama?.error = getString(R.string.empty)
                }
                deskripsi.isEmpty() -> {
                    binding?.edDesk?.error = getString(R.string.empty)
                }
            }
            if(pengguna != null){
                val pengguna = Pengguna(
                    pengguna?.id,
                    nama,
                    deskripsi,
                )
                penggunaViewModel.updatePengguna(pengguna)

            }else{
                val pengguna = Pengguna(
                    null,
                    nama,
                    deskripsi,
                )
                penggunaViewModel.insertPengguna(pengguna as Pengguna)
            }
            dismiss()
        }

    }

    private fun obtainViewModel(activity : AppCompatActivity): PenggunaViewModel{
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[PenggunaViewModel::class.java]
    }
}