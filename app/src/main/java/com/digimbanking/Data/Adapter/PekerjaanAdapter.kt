package com.digimbanking.Data.Adapter

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat.getExternalCacheDirs
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.core.domain.model.PekerjaanItemModel
import com.core.domain.model.PenghasilanItemModel
import com.digimbanking.Features.Auth.CreateRekening.Cif.BuatAkun
import com.digimbanking.databinding.ListPekerjaanBinding
import com.digimbanking.databinding.ListPenghasilanBinding

class PekerjaanAdapter(private val onItemClick: (PekerjaanItemModel) -> Unit):
    RecyclerView.Adapter<PekerjaanAdapter.ViewHolder>() {

    private var pekerjaanList = mutableListOf<PekerjaanItemModel>()

    inner class ViewHolder(private val binding: ListPekerjaanBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PekerjaanItemModel) {
            binding.txtJob.text = item.nama
            binding.root.setOnClickListener { onItemClick(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PekerjaanAdapter.ViewHolder {
        val binding =
            ListPekerjaanBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PekerjaanAdapter.ViewHolder, position: Int) {
        val item = pekerjaanList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return pekerjaanList.size
    }

    fun setPenghasilanList(newList: List<PekerjaanItemModel>) {
        pekerjaanList.clear()
        pekerjaanList.addAll(newList)
        notifyDataSetChanged()
    }

}