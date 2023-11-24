package com.digimbanking.di

import android.content.Context
import com.core.data.local.preferences.UserPreferencesImpl
import com.core.data.repositories.EmailRepositorySdh
import com.core.data.repositories.PasswordRepositroySdh
import com.core.di.ApiContractAdaRekening
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideUserPreferencesDataStore(@ApplicationContext context: Context): UserPreferencesImpl {
        return UserPreferencesImpl(context)
    }

    @Singleton
    @Provides
    fun provideUserEmailRepositorySdh (
        apiService: ApiContractAdaRekening,
        userPreferences: UserPreferencesImpl
    ): EmailRepositorySdh {
        return EmailRepositorySdh(apiService, userPreferences)
    }

    @Singleton
    @Provides
    fun provideUserPasswordRepositorySdh (
        apiService: ApiContractAdaRekening,
        userPreferences: UserPreferencesImpl
    ): PasswordRepositroySdh {
        return PasswordRepositroySdh(apiService, userPreferences)
    }
}