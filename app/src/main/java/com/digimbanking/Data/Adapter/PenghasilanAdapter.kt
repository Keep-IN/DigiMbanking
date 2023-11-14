package com.digimbanking.Data.Adapter

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.core.domain.model.PekerjaanItemModel
import com.core.domain.model.PenghasilanItemModel
import com.digimbanking.Features.Auth.CreateRekening.Cif.BuatAkun
import com.digimbanking.databinding.ListPenghasilanBinding

class PenghasilanAdapter(private val onItemClick: (PenghasilanItemModel) -> Unit):
    RecyclerView.Adapter<PenghasilanAdapter.ViewHolder>() {

    private var penghasilanList = mutableListOf<PenghasilanItemModel>()

    inner class ViewHolder(private val binding: ListPenghasilanBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PenghasilanItemModel) {
            binding.txtPenghasilan.text = item.nama
            binding.root.setOnClickListener { onItemClick(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ListPenghasilanBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = penghasilanList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return penghasilanList.size
    }

    fun setPenghasilanList(newList: List<PenghasilanItemModel>) {
        penghasilanList.clear()
        penghasilanList.addAll(newList)
        notifyDataSetChanged()
    }
}
