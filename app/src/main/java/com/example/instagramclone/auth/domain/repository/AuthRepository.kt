package com.example.instagramclone.auth.domain.repository

import com.example.instagramclone.core.util.Resource
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun loginWithEmail(email: String, password: String): Flow<Resource<AuthResult>>
}