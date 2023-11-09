package com.digimbanking.Data.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.core.domain.model.PekerjaanItemModel
import com.digimbanking.databinding.ListPekerjaanBinding

class PekerjaanAdapter: RecyclerView.Adapter<PekerjaanAdapter.ViewHolder>() {
    private val itemListener : ((PekerjaanItemModel) -> Unit)? = null
    private val data : MutableList<PekerjaanItemModel> = mutableListOf()
    inner class ViewHolder(private val binding: ListPekerjaanBinding): RecyclerView.ViewHolder(binding.root) {
        fun setData(item: PekerjaanItemModel, listener: ((PekerjaanItemModel) -> Unit)?){
            binding.root.setOnClickListener{
                listener?.invoke(item)
            }
            binding.txtJob.text = item.nama
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ListPekerjaanBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int = data.size

    fun submitList(list: List<PekerjaanItemModel>) {
        val initSize = itemCount
        data.clear()
        notifyItemRangeRemoved(0, initSize)
        data.addAll(list)
        notifyItemRangeInserted(0, data.size)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(data[position], itemListener)
    }

    fun setOnclickItem(holder: ViewHolder, position: Int){
        holder.setData(data[position], itemListener)
    }
}