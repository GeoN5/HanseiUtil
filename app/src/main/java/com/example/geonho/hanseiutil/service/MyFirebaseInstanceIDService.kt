@file:Suppress("DEPRECATION")

package com.example.geonho.hanseiutil.service

import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService

class MyFirebaseInstanceIDService : FirebaseInstanceIdService() {
    override fun onTokenRefresh() {
        val refreshedToken = FirebaseInstanceId.getInstance().token
        println("Refreshed token: " + refreshedToken!!)
    }
}

