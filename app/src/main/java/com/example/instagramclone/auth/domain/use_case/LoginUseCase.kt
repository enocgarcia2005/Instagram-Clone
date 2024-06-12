// LoginUseCase.kt
package com.example.instagramclone.auth.domain.use_case

import com.example.instagramclone.auth.domain.repository.AuthRepository
import com.example.instagramclone.core.util.Resource
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(email: String, password: String): Flow<Resource<AuthResult>> {
        val validationResult = validateCredentials(email, password)
        return if (validationResult.isEmpty()) {
            authRepository.loginWithEmail(email, password)
        } else {
            flow {
                emit(Resource.Error(validationResult))
            }
        }
    }

    private fun validateCredentials(email: String, password: String): Map<String, String> {
        val errors = mutableMapOf<String, String>()

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            errors["email"] = "Invalid email address"
        }

        if (password.length < 6) {
            errors["password"] = "Password must be at least 6 characters"
        }

        return errors
    }
}
