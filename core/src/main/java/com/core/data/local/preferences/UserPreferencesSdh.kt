package com.core.data.local.preferences

interface UserPreferencesSdh {

    suspend fun getIdd(): Int

    suspend fun setId(id : Int)
}