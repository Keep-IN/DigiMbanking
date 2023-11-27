package com.core.data.lokal.preferences

import kotlinx.coroutines.flow.Flow


interface UserPreferences {

    suspend fun getUser() : Int

    suspend fun setId(id : Int)

    suspend fun setCardId(id: Int)

    suspend fun getCard() : Int
}