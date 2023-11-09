package com.digimbanking.Data.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.digimbanking.Data.Model.RiwayatItemModel
import com.digimbanking.R
import com.digimbanking.databinding.RiwayatTransakiListViewBinding

class RiwayatTransaksiListAdapter : RecyclerView.Adapter<RiwayatTransaksiListAdapter.ViewHolder>()
{
    private var itemListener: ((RiwayatItemModel) -> Unit)? = null
    private val data: MutableList<RiwayatItemModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RiwayatTransaksiListAdapter.ViewHolder {
        return ViewHolder(
            RiwayatTransakiListViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        holder.setData(data[position], itemListener)
    }
    override fun getItemCount(): Int = data.size

    fun submitList(list: List<RiwayatItemModel>){
        val initSize = itemCount
        data.clear()
        notifyItemRangeRemoved(0, initSize)
        data.addAll(list)
        notifyItemRangeInserted(0, data.size)
    }

    inner class ViewHolder(private val binding: RiwayatTransakiListViewBinding): RecyclerView.ViewHolder(binding.root){
        fun setData(item: RiwayatItemModel,listener: ((RiwayatItemModel) -> Unit)?){
            when(item.tipeTransaksi){
                "kredit" -> binding.ivTransaksiMasuk.setImageResource(R.drawable.ic_masuk)
                "debit" -> binding.ivTransaksiMasuk.setImageResource(R.drawable.ic_keluar)
            }
        }
    }

    fun setOnClickItem(listener: ((RiwayatItemModel) -> Unit)?){
        this.itemListener = listener
    }
}