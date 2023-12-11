package com.digimbanking.Features.Akun

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.core.data.network.Result
import com.digimbanking.Data.Adapter.AkunListAdapter
import com.digimbanking.R
import com.digimbanking.databinding.FragmentAkunBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AkunFragment : Fragment() {
    private lateinit var binding: FragmentAkunBinding
    private val adapterAkun: AkunListAdapter by lazy { AkunListAdapter() }
    private lateinit var viewModel: AkunViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentAkunBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(activity)
        binding.rvListAkun.layoutManager = layoutManager
        binding.rvListAkun.adapter = adapterAkun

        viewModel = ViewModelProvider(this)[AkunViewModel::class.java]
        viewModel.viewModelScope.launch(Dispatchers.Main) {
            onLoading()
            activity?.let {
                viewModel.doAkun().observe(viewLifecycleOwner){result ->
                    when(result){
                        is Result.Success -> {
                            Log.d("Isi Akun", "${result.data}")
                            adapterAkun.submitList(result.data.data.rekening)
                            onFinishedLoading()
                        }
                        is Result.Error -> {
                            Log.d("Error Get Akun", result.errorMessage)
                            onFinishedLoading()
                        }
                        else -> {
                            Log.d("Unexpected Result", result.toString())
                            onLoading()
                        }
                    }
                }
            }
        }

        binding.buttonKartuBaru.setOnClickListener {
            Toast.makeText(requireContext(), "Tombol Kartu Baru diklik", Toast.LENGTH_SHORT).show()
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun onLoading(){
        binding.loadScreenAkun.visibility = View.VISIBLE
        binding.loadScreenAkun.setOnTouchListener { _, _ ->
            true
        }
    }

    private fun onFinishedLoading(){
        binding.loadScreenAkun.visibility = View.GONE
    }
}