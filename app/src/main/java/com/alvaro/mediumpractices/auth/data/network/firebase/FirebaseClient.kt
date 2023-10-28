package com.alvaro.mediumpractices.auth.data.network.firebase


import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import javax.inject.Inject
import javax.inject.Singleton


//Creo una unica Instancia
@Singleton
class FirebaseClient @Inject constructor() {
    val auth:FirebaseAuth = FirebaseAuth.getInstance()
    val db = Firebase.firestore
}