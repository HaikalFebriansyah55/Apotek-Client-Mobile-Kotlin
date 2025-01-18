package com.example.uas.firebase

data class LoginState(
    val success: String? = "",
    val error: String? = "",
    val loading : Boolean = false
)
