package com.digimbanking.Features.Profile.UbahMpin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import com.digimbanking.Features.Profile.UbahPw.UbahPwViewModel
import com.digimbanking.R
import com.digimbanking.databinding.ActivityUbahMpinBinding
import com.digimbanking.databinding.ActivityUbahPwBinding

class UbahMpin : AppCompatActivity() {
    private lateinit var binding: ActivityUbahMpinBinding
    private lateinit var ubahMpinViewModel: UbahMpinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityUbahMpinBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.apply {
            tilMpinLama.editText?.doOnTextChanged { text, start, before, count ->
                if (ubahMpinViewModel.validateMpinLama(text.toString())) {
                    binding.tilMpinLama.isErrorEnabled = false
                    binding.btnSimpanMpin.isEnabled = true
                } else {
                    binding.tilMpinLama.isErrorEnabled = true
                    binding.btnSimpanMpin.isEnabled = false
                    binding.tilMpinLama.error = "MPIN harus terdiri dari 6 digit"
                }
            }
            tilMpinBaru.editText?.doOnTextChanged { text, start, before, count ->
                if (ubahMpinViewModel.validateMpinBaru(text.toString())) {
                    if (ubahMpinViewModel.validateMpinBeda(
                            binding.tilMpinLama.editText?.text.toString(),
                            binding.tilMpinBaru.editText?.text.toString()
                        )
                    ) {
                        binding.tilMpinBaru.isErrorEnabled = false
                        binding.btnSimpanMpin.isEnabled = true
                    } else {
                        binding.tilMpinBaru.isErrorEnabled = true
                        binding.btnSimpanMpin.isEnabled = false
                        binding.tilMpinBaru.error = "MPIN harus berbeda dengan MPIN Lama"
                    }
                } else {
                    binding.tilMpinBaru.isErrorEnabled = true
                    binding.btnSimpanMpin.isEnabled = false
                    binding.tilMpinBaru.error = "MPIN harus terdiri dari 6 digit"
                }
            }
            tilKonfirmMpin.editText?.doOnTextChanged { text, start, before, count ->
                if (ubahMpinViewModel.validateMpinKonfirm(text.toString())) {
                    if (ubahMpinViewModel.validateMpinSama(
                            binding.tilMpinBaru.editText?.text.toString(),
                            binding.tilKonfirmMpin.editText?.text.toString()
                        )
                    ) {
                        binding.tilKonfirmMpin.isErrorEnabled = false
                        binding.btnSimpanMpin.isEnabled = true
                    } else {
                        binding.tilKonfirmMpin.isErrorEnabled = true
                        binding.btnSimpanMpin.isEnabled = false
                        binding.tilKonfirmMpin.error = "MPIN tidak sama"
                    }
                } else {
                    binding.tilMpinBaru.isErrorEnabled = true
                    binding.btnSimpanMpin.isEnabled = false
                    binding.tilMpinBaru.error = "MPIN harus terdiri dari 6 digit"
                }
            }
            ivBackUbahMpin.setOnClickListener{
                onBackPressedDispatcher.onBackPressed()
            }

            validateInput()

        }

    }
    private fun validateInput(){
        val mpinLama = binding.tilMpinLama.editText?.text.toString()
        val mpinBaru = binding.tilMpinBaru.editText?.text.toString()
        val mpinKonfirm = binding.tilKonfirmMpin.editText?.text.toString()

        val isMpinLamaValid = ubahMpinViewModel.validateMpinLama(mpinLama)
        val isMpinBaruValid = ubahMpinViewModel.validateMpinBaru(mpinBaru)
        val isMpinKonfirm = ubahMpinViewModel.validateMpinKonfirm(mpinKonfirm)

        binding.btnSimpanMpin.isEnabled = isMpinLamaValid && isMpinBaruValid && isMpinKonfirm
    }
}