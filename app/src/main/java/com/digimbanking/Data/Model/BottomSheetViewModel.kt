package com.digimbanking.Data.Model

import androidx.lifecycle.ViewModel

class BottomSheetViewModel: ViewModel() {
    val data = arrayOf("Item 1", "Item 2", "Item 3")

    fun onItemClick(item: String){

    }
}