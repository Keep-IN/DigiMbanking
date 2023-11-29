package com.digimbanking.Data.Adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.core.data.response.riwayatTransaksi.Transaction
import com.core.domain.model.RiwayatItemModel
import com.digimbanking.R
import com.digimbanking.databinding.RiwayatTransakiListViewBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class RiwayatTransaksiListAdapter : RecyclerView.Adapter<RiwayatTransaksiListAdapter.ViewHolder>()
{
    private var itemListener: ((Transaction) -> Unit)? = null
    private val data: MutableList<Transaction> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RiwayatTransaksiListAdapter.ViewHolder {
        return ViewHolder(
            RiwayatTransakiListViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        holder.setData(data[position], itemListener)
    }
    override fun getItemCount(): Int = data.size

    fun submitList(list: List<Transaction>){
        val initSize = itemCount
        data.clear()
        notifyItemRangeRemoved(0, initSize)
        data.addAll(list)
        notifyItemRangeInserted(0, data.size)
    }

    inner class ViewHolder(private val binding: RiwayatTransakiListViewBinding): RecyclerView.ViewHolder(binding.root){
        fun setData(item: Transaction, listener: ((Transaction) -> Unit)?){
            binding.root.setOnClickListener{
                listener?.invoke(item)
            }
            val convertedDate = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
            val originalDate: Date = convertedDate.parse(item.tanggal)
            val targetDateFormat = SimpleDateFormat("dd MMM yyyy | HH:mm", Locale.US)
            binding.tvDateRiwayatTransfer.text = targetDateFormat.format(originalDate).toString()
            when(item.tipeTransaksi){
                "KREDIT" -> {
                    binding.tvTransaksiMasuk.text = "Transaksi Masuk"
                    binding.ivTransaksiMasuk.setImageResource(R.drawable.ic_masuk)
                    binding.tvNominalRiwayatTransaksiMasuk.text = "+ Rp.${item.jumlahTransaksi.toLong().formatDotSeparator()}"
                    binding.tvNominalRiwayatTransaksiMasuk.setTextColor(Color.parseColor("#25AC57"))
                }
                "DEBIT" -> {
                    binding.tvTransaksiMasuk.text = "Transaksi Keluar"
                    binding.ivTransaksiMasuk.setImageResource(R.drawable.ic_keluar)
                    binding.tvNominalRiwayatTransaksiMasuk.text = "- Rp.${item.jumlahTransaksi.toLong().formatDotSeparator()}"
                    binding.tvNominalRiwayatTransaksiMasuk.setTextColor(Color.parseColor("#E71414"))
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