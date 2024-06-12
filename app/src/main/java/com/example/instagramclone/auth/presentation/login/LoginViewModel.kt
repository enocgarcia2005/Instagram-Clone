package com.example.instagramclone.auth.presentation.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.instagramclone.auth.domain.use_case.LoginUseCase
import com.example.instagramclone.core.navigation.NavHostType
import com.example.instagramclone.core.presentation.state.TextFieldState
import com.example.instagramclone.core.presentation.state.UiState
import com.example.instagramclone.core.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _eventFlow = MutableSharedFlow<UiState>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val _loginState = Channel<LoginState>()
    val loginState = _loginState.receiveAsFlow()

    private val _emailState = mutableStateOf(TextFieldState())
    val emailState: State<TextFieldState> = _emailState

    private val _passwordState = mutableStateOf(TextFieldState())
    val passwordState: State<TextFieldState> = _passwordState

    fun setEmail(text: String) {
        _emailState.value = _emailState.value.copy(
            text = text,
            error = null
        )
    }

    fun setPassword(text: String) {
        _passwordState.value = _passwordState.value.copy(
            text = text,
            error = null
        )
    }


    fun loginWithEmail() = viewModelScope.launch {
        loginUseCase.invoke(emailState.value.text, passwordState.value.text).collect { result ->
            when (result) {
                is Resource.Loading -> {
                    _loginState.send(LoginState(isLoading = true))
                }

                is Resource.Success -> {
                    _loginState.send(LoginState(isLoading = false))

                    _eventFlow.emit(UiState.NavigateEvent(NavHostType.AUTHENTICATED.ROUTE))
                }

                is Resource.Error -> {
                    if (result.errorMessages["email"] != null) {
                        _emailState.value = emailState.value.copy(
                            error = result.errorMessages["email"]
                        )
                    }

                    if (result.errorMessages["password"] != null) {
                        _passwordState.value = passwordState.value.copy(
                            error = result.errorMessages["password"]
                        )
                    }
                    _loginState.send(LoginState(isLoading = false))
                }
            }
        }
    }
}