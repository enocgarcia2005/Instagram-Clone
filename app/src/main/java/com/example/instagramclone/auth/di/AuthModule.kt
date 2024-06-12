package com.example.instagramclone.auth.di

import com.example.instagramclone.auth.data.repository.AuthRepositoryImpl
import com.example.instagramclone.auth.domain.use_case.LoginUseCase
import com.example.instagramclone.auth.presentation.login.LoginViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun providesAuthRepositoryImpl(firebaseAuth: FirebaseAuth) : AuthRepositoryImpl {
        return AuthRepositoryImpl(firebaseAuth)
    }

    @Provides
    @Singleton
    fun providesLoginUseCase(authRepository: AuthRepositoryImpl) : LoginUseCase {
        return LoginUseCase(authRepository)
    }

    @Provides
    @Singleton
    fun provideLoginViewModel(useCase: LoginUseCase): LoginViewModel = LoginViewModel(useCase)
}