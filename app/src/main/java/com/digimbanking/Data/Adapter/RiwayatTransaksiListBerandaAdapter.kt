package com.digimbanking.Data.Adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.core.data.response.riwayatTransaksi.Transaction
import com.core.domain.model.RiwayatItemModel
import com.digimbanking.R
import com.digimbanking.databinding.RiwayatBerandaListTransaksiViewBinding

class RiwayatTransaksiListBerandaAdapter : RecyclerView.Adapter<RiwayatTransaksiListBerandaAdapter.ViewHolder>() {
    private var itemListener: ((Transaction) -> Unit)? = null
    private val data: MutableList<Transaction> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RiwayatTransaksiListBerandaAdapter.ViewHolder {
        return ViewHolder(
            RiwayatBerandaListTransaksiViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        holder.setData(data[position], itemListener)
    }
    override fun getItemCount(): Int = data.size

    fun submitList(list: List<Transaction>){
        data.clear()
        val limitedList = if(list.size > 4){
            list.subList(0,4)
        }else{
            list
        }
        data.addAll(limitedList.sortedByDescending { it.tanggal })
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: RiwayatBerandaListTransaksiViewBinding): RecyclerView.ViewHolder(binding.root){
        fun setData(item: Transaction, listener: ((Transaction) -> Unit)?){
            binding.root.setOnClickListener{
                listener?.invoke(item)
            }
            when(item.tipeTransaksi){
                "KREDIT" -> {
                    binding.tvTransaksiMasuk.text = "Transaksi Masuk"
                    binding.ivTransaksiMasuk.setImageResource(R.drawable.ic_masuk)
                    binding.tvNominalRiwayatTransaksiMasukBeranda.text = "+ Rp${item.jumlahTransaksi.toLong().formatDotSeparator()}"
                    binding.tvNominalRiwayatTransaksiMasukBeranda.setTextColor(Color.parseColor("#25AC57"))
                }
                "DEBIT" -> {
                    binding.tvTransaksiMasuk.text = "Transaksi Keluar"
                    binding.ivTransaksiMasuk.setImageResource(R.drawable.ic_keluar)
                    binding.tvNominalRiwayatTransaksiMasukBeranda.text = "- Rp${item.jumlahTransaksi.toLong().formatDotSeparator()}"
                    binding.tvNominalRiwayatTransaksiMasukBeranda.setTextColor(Color.parseColor("#E71414"))
                }
            }
        }
    }

    fun setOnClickItem(listener: ((Transaction) -> Unit)?){
        this.itemListener = listener
    }

    fun Long.formatDotSeparator(): String{
        return toString()
            .reversed()
            .chunked(3)
            .joinToString (".")
            .reversed()
    }
}