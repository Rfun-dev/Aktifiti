package com.listview.aktifiti.ui.ui.fragment

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.listview.aktifiti.R
import com.listview.aktifiti.databinding.FragmentHomepageBinding
import com.listview.aktifiti.db.entities.Pengguna
import com.listview.aktifiti.helper.ViewModelFactory
import com.listview.aktifiti.ui.ui.adapter.PenggunaAdapter
import com.listview.aktifiti.ui.ui.viemodel.PenggunaViewModel

class HomepageFragment : Fragment(), PenggunaAdapter.OnClickPenggunaListener {
    private var _binding : FragmentHomepageBinding? = null
    private val binding get() = _binding
    private lateinit var adapter : PenggunaAdapter
    var pengguna : Pengguna? = null

    private lateinit var penggunaViewModel: PenggunaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomepageBinding.inflate(layoutInflater,container,false)
        return binding?.root
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        penggunaViewModel = obtainViewModel(activity as AppCompatActivity)

        adapter = PenggunaAdapter(activity as FragmentActivity,penggunaViewModel)
        penggunaViewModel.getAllPengguna().observe(viewLifecycleOwner){
            if(it != null){
                adapter.listPengguna = it
                binding?.rvPengguna?.adapter = adapter
            }
        }

        adapter.addOnClickListener(this)

        binding?.rvPengguna?.layoutManager = LinearLayoutManager(context)
        binding?.rvPengguna?.setHasFixedSize(true)



        binding?.fabPengguna?.setOnClickListener {
            val dialog = AddPenggunaFragment()
            dialog.show(activity?.supportFragmentManager as FragmentManager,"customDialog")
        }
    }

    private fun obtainViewModel(activity : AppCompatActivity): PenggunaViewModel{
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[PenggunaViewModel::class.java]
    }

    override fun onCLick(pengguna: Pengguna) {
        val toKegiatanFragment = HomepageFragmentDirections.actionHomepageFragment2ToKegiatanFragment(pengguna)
        view?.findNavController()?.navigate(toKegiatanFragment)
    }


}