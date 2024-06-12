package com.example.instagramclone.core.util

sealed class Resource<out T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(val errorMessages: Map<String, String>, data: T? = null) : Resource<T>(data) {
        constructor(errorType: String, errorMessage: String, data: T? = null) : this(mapOf(errorType to errorMessage), data)
    }
    class Loading<T>(data: T? = null) : Resource<T>(data)
}