package com.core.data.local.entity

data class User(
    val isLogin: Boolean,

    var token: String? = null,
    var email: String? = null,
    var password: String? = null,
)