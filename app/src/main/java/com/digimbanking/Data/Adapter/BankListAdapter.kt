package com.digimbanking.Data.Adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.core.domain.model.BankItemModel
import com.digimbanking.databinding.BankListViewBinding

class BankListAdapter: RecyclerView.Adapter<BankListAdapter.ViewHolder>() {
    private var itemListener: ((BankItemModel) -> Unit)? = null
    private val data: MutableList<BankItemModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            BankListViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(data[position], itemListener)
    }

    override fun getItemCount(): Int = data.size

    fun submitList(list: List<BankItemModel>) {
        val initSize = itemCount
        data.clear()
        notifyItemRangeRemoved(0, initSize)
        data.addAll(list)
        notifyItemRangeInserted(0, data.size)
    }

    inner class ViewHolder(private val binding: BankListViewBinding): RecyclerView.ViewHolder(binding.root) {
        fun setData(item: BankItemModel, listener: ((BankItemModel) -> Unit)?){
            binding.root.setOnClickListener{
                listener?.invoke(item)
            }
            binding.tvNamaBank.text = item.nama
        }
    }

    fun setOnclickItem(listener: ((BankItemModel) -> Unit)?){
        this.itemListener = listener
    }

}