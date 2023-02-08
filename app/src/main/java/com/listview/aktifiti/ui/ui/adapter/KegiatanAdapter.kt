package com.listview.aktifiti.ui.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.listview.aktifiti.R
import com.listview.aktifiti.databinding.ItemKegiatanBinding
import com.listview.aktifiti.db.entities.Kegiatan

class KegiatanAdapter(val context: Context) : RecyclerView.Adapter<KegiatanAdapter.ViewHolder>() {
    var listKegiatan = listOf<Kegiatan>()
    class ViewHolder(val binding: ItemKegiatanBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemKegiatanBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.binding){
            listKegiatan[position].let { data ->
                tvKegiatan.text = data.kegiatan
                if(listKegiatan.last() == listKegiatan[position]){
                    tvWaktu.text = context.resources.getString(R.string.waktu_pengkondisian,data.waktu)
                }
                else{
                    tvWaktu.text = context.resources.getString(R.string.waktu,listKegiatan[position].waktu,data.waktu)
                }
            }
        }
    }

    override fun getItemCount(): Int = listKegiatan.size
}