package com.example.instagramclone.core.navigation

sealed class NavHostType {
    object ANONYMOUS {
        const val ROUTE = "anonymous"
    }

    object AUTHENTICATED {
        const val ROUTE = "authenticated"
    }
}