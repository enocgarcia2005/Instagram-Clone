package com.example.instagramclone.auth.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.instagramclone.R
import com.example.instagramclone.auth.presentation.component.AuthButton
import com.example.instagramclone.auth.presentation.component.LoginTextField
import com.example.instagramclone.auth.presentation.component.OutlinedAuthButton
import com.example.instagramclone.core.presentation.state.UiState
import kotlinx.coroutines.flow.collectLatest


@Composable
fun LoginScreen(
    navController: NavHostController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val emailState = viewModel.emailState.value
    val passwordState = viewModel.passwordState.value
    val loginState = viewModel.loginState.collectAsState(initial = null)
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    val icon = if (passwordVisible)
        ImageVector.vectorResource(id = R.drawable.eye_open)
    else ImageVector.vectorResource(id = R.drawable.eye_closed)
    val description = if (passwordVisible) "Hide password" else "Show password"

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is UiState.NavigateEvent -> {
                    navController.navigate(event.route)
                }
            }
        }
    }
    Scaffold { it ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
            contentAlignment = Alignment.Center,
        ) {
            if (loginState.value?.isLoading == true) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(24.dp)
                )
            }

            Column(
                modifier = Modifier
                    .align(Alignment.TopCenter),
                verticalArrangement = Arrangement.spacedBy(50.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                TextButton(
                    onClick = {},
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Text(
                        text = "English",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.outline,
                    )
                }
                Image(
                    painter = painterResource(id = R.mipmap.ic_instagram_foreground),
                    contentDescription = "Logo",
                    modifier = Modifier.size(120.dp)
                )

            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                LoginTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = emailState.text,
                    onValueChange = {
                        viewModel.setEmail(it)
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                    ),
                    isError = emailState.error != null,
                    label = "Email",
                    errorMessage = emailState.error ?: "",
                )

                LoginTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = passwordState.text,
                    onValueChange = {
                        viewModel.setPassword(it)
                    },
                    label = "Password",
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    isError = passwordState.error != null,
                    icon = icon,
                    iconDescription = description,
                    onIconPress = {
                        passwordVisible = !passwordVisible
                    },
                    errorMessage = passwordState.error ?: "",
                )
                AuthButton(
                    onClick = {
                        viewModel.loginWithEmail()
                    },
                    enabled = passwordState.error == null && emailState.error == null,
                    text = "Log In",
                )
                TextButton(
                    onClick = {},
                    contentPadding = PaddingValues(0.dp),
                ) {
                    Text(
                        text = "Forgot Password?",
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onBackground,
                    )
                }
            }
            OutlinedAuthButton(
                text = "Sign Up",
                onClick = { },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
            )
        }
    }
}

