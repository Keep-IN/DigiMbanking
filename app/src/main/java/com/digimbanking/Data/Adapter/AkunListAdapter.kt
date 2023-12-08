package com.digimbanking.Data.Adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.core.data.response.akun.Rekening
import com.digimbanking.R
import com.digimbanking.databinding.AkunListViewBinding

class AkunListAdapter: RecyclerView.Adapter<AkunListAdapter.ViewHolder>() {
    private var itemListener: ((Rekening) -> Unit)? = null
    private val data: MutableList<Rekening> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AkunListAdapter.ViewHolder {
        return ViewHolder(
            AkunListViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: AkunListAdapter.ViewHolder, position: Int) {
        holder.setData(data[position], itemListener)
    }

    override fun getItemCount(): Int = data.size

    fun submitList(list: List<Rekening>){
        val initSize = itemCount
        data.clear()
        notifyItemRangeRemoved(0, initSize)
        data.addAll(list)
        notifyItemRangeInserted(0, data.size)
    }

    inner class ViewHolder(private val binding: AkunListViewBinding): RecyclerView.ViewHolder(binding.root){
        fun setData(item: Rekening, listener: ((Rekening) -> Unit)?){
            binding.root.setOnClickListener {
                listener?.invoke(item)
            }
            binding.txtNomorRekening.text = item.noRekening
            binding.txtDebit.text = "Debit"
            binding.txtLimit5.text = item.tipeRekening.limitTransfer
            when(item.tipeRekening.idTipe){
                1 -> {
                    binding.txtJenisKartu.text = "Silver"
                    binding.imgKotak.setImageResource(R.drawable.kotak)
                    binding.cvBgCard.setCardBackgroundColor(Color.parseColor("#BBBBBB"))
                    binding.imgLogoBank.setImageResource(R.drawable.gpn)
                }
                2 -> {
                    binding.txtJenisKartu.text = "Gold"
                    binding.imgKotak.setImageResource(R.drawable.kotak)
                    binding.cvBgCard.setCardBackgroundColor(Color.parseColor("#FBDB2F"))
                    binding.imgLogoBank.setImageResource(R.drawable.ic_visa_logo)
                }
                3 -> {
                    binding.txtJenisKartu.text = "Platinum"
                    binding.imgKotak.setImageResource(R.drawable.kotak)
                    binding.cvBgCard.setCardBackgroundColor(Color.parseColor("#696865"))
                    binding.imgLogoBank.setImageResource(R.drawable.platinum)
                }
            }
        }
    }

    fun setOnClickItem(listener: ((Rekening) -> Unit)?){
        this.itemListener = listener
    }
}