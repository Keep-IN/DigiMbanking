package com.digimbanking.Data.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.digimbanking.Data.Model.RiwayatItemModel
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


}