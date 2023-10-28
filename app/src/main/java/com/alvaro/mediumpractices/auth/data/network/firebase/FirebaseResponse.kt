package com.alvaro.mediumpractices.auth.data.network.firebase

sealed class FirebaseResponse{
    object Success:FirebaseResponse()
    data class Error(val error:String):FirebaseResponse()
}
