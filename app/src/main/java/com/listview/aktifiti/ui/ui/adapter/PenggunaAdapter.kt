package com.listview.aktifiti.ui.ui.adapter

import android.app.AlertDialog
import android.app.Dialog
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.listview.aktifiti.R
import com.listview.aktifiti.databinding.ItemPenggunaBinding
import com.listview.aktifiti.db.entities.Pengguna
import com.listview.aktifiti.ui.ui.fragment.AddPenggunaFragment
import com.listview.aktifiti.ui.ui.viemodel.PenggunaViewModel

class PenggunaAdapter(val activity: FragmentActivity,val viewModel: PenggunaViewModel) : RecyclerView.Adapter<PenggunaAdapter.ViewHolder>() {
    var listPengguna = listOf<Pengguna>()
    private var listener : OnClickPenggunaListener? = null

    inner class ViewHolder(val binding: ItemPenggunaBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPenggunaBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            listPengguna[position].let{ pengguna ->
                binding.tvNama.text = pengguna.nama
                binding.tvDeskripsi.text = pengguna.deskripsi
                binding.clPengguna.setOnClickListener {
                    listener?.onCLick(pengguna)
                }
                binding.btnDelete.setOnClickListener {
                    val builder = AlertDialog.Builder(activity)
                    builder.setTitle("Menghapus data")
                    builder.setMessage("Apakah anda ingin menghapus data ini ?")

                    //set negative button
                    builder.setPositiveButton(
                        "Submit") { _, _ ->
                        viewModel.deletePengguna(pengguna)
                        Toast.makeText(activity,"Berhasil dihapus",Toast.LENGTH_SHORT).show()
                    }

                    builder.setNegativeButton(
                        "Cancel") { dialog, id ->
                        dialog.dismiss()
                    }
                    builder.show()
                }
                binding.btnEdit.setOnClickListener {
                    val dialog = AddPenggunaFragment()
                    dialog.pengguna = pengguna
                    dialog.show(activity.supportFragmentManager,"customDialog")
                }
            }
        }
    }

    fun addOnClickListener(onClickListener : OnClickPenggunaListener) {
        listener = onClickListener
    }

    interface OnClickPenggunaListener{
        fun onCLick(pengguna: Pengguna)
    }

    override fun getItemCount(): Int = listPengguna.size
}