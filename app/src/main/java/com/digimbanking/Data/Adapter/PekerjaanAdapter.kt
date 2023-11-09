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
import com.digimbanking.Features.Auth.CreateRekening.Cif.BuatAkun
import com.digimbanking.databinding.ListPekerjaanBinding

class PekerjaanAdapter(private val context: Context, private val itemListener : List<PekerjaanItemModel>): RecyclerView.Adapter<PekerjaanAdapter.ViewHolder>() {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("job", Context.MODE_PRIVATE)

    class ViewHolder( val binding: ListPekerjaanBinding): RecyclerView.ViewHolder(binding.root) {

        fun setData(item: PekerjaanItemModel, sharedPreferences: SharedPreferences){
            binding.txtJob.setOnClickListener {
                val editor:SharedPreferences.Editor = sharedPreferences.edit()
                editor.putString("pekerjaan", item.nama)
                editor.apply()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ListPekerjaanBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemListener[position]
        holder.setData(item, sharedPreferences)
        holder.binding.txtJob.text = item.nama

        holder.binding.txtJob.setOnClickListener {
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putString("pekerjaan", item.nama)
            editor.apply()

            val intent = Intent(it.context, BuatAkun::class.java)
            intent.putExtra("pekerjaan", item.nama)
            it.context.startActivity(intent)


        }
    }

    override fun getItemCount(): Int {
        return itemListener.size
    }



}