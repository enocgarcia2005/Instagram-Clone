package com.example.instagramclone.core.presentation.state

sealed class UiState {
    data class NavigateEvent(val route: String) : UiState()
}