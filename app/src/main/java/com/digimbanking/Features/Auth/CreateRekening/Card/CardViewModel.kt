package com.digimbanking.Features.Auth.CreateRekening.Card

import androidx.lifecycle.ViewModel
import com.core.data.repositories.CardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CardViewModel @Inject constructor(
    private val cardRepository: CardRepository
) : ViewModel() {
    fun getCard() = cardRepository.getCard()
}
