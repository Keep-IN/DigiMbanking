package com.digimbanking.Data.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.core.domain.model.RiwayatItemModel
import com.digimbanking.Data.Model.RiwayatModel
import com.digimbanking.R
import com.digimbanking.databinding.RiwayatBerandaListTransaksiViewBinding

class RiwayatTransaksiListBerandaAdapter : RecyclerView.Adapter<RiwayatTransaksiListBerandaAdapter.ViewHolder>() {
    private var itemListener: ((RiwayatItemModel) -> Unit)? = null
    private val data: MutableList<RiwayatItemModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RiwayatTransaksiListBerandaAdapter.ViewHolder {
        return ViewHolder(
            RiwayatBerandaListTransaksiViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        holder.setData(data[position], itemListener)
    }
    override fun getItemCount(): Int = 5

    fun submitList(list: List<RiwayatItemModel>){
        val initSize = itemCount
        data.clear()
        notifyItemRangeRemoved(0, initSize)
        data.addAll(list)
        notifyItemRangeInserted(0, data.size)
    }

    inner class ViewHolder(private val binding: RiwayatBerandaListTransaksiViewBinding): RecyclerView.ViewHolder(binding.root){
        fun setData(item: RiwayatItemModel, listener: ((RiwayatItemModel) -> Unit)?){
            when(item.tipeTransaksi){
                "kredit" -> {
                    binding.tvTransaksiMasuk.text = "Transaksi Masuk"
                    binding.ivTransaksiMasuk.setImageResource(R.drawable.ic_masuk)
                }
                "debit" -> {
                    binding.tvTransaksiMasuk.text = "Transaksi Keluar"
                    binding.ivTransaksiMasuk.setImageResource(R.drawable.ic_keluar)
                }
            }
        }
    }

    fun setOnClickItem(listener: ((RiwayatItemModel) -> Unit)?){
        this.itemListener = listener
    }
}