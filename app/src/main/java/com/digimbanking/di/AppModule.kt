package com.digimbanking.di

import android.content.Context
import com.core.data.lokal.preferences.UserPreferences
import com.core.data.lokal.preferences.UserPreferencesImpl
import com.core.data.repositories.CardRepository
import com.core.data.repositories.MpinRepository
import com.core.data.repositories.OtpRepository
import com.core.data.repositories.PasswordRepository
import com.core.di.ApiContractCreateRekening
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideUserPreferences(userPreferencesImpl: UserPreferencesImpl): UserPreferences {
        return userPreferencesImpl
    }
    @Singleton
    @Provides
    fun provideUserPreferencesDataStore(@ApplicationContext context: Context): UserPreferencesImpl {
        return UserPreferencesImpl(context)
    }

    @Singleton
    @Provides
    fun provideUserEmailRepositorySdh (
        apiService: ApiContractCreateRekening,
        userPreferences: UserPreferencesImpl
    ): OtpRepository {
        return OtpRepository(apiService, userPreferences)
    }

    @Singleton
    @Provides
    fun provideCardList (
        apiService: ApiContractCreateRekening,
        userPreferences: UserPreferencesImpl
    ): CardRepository {
        return CardRepository(apiService, userPreferences)
    }

    @Singleton
    @Provides
    fun provideUserPasswordRepositorySdh (
        apiService: ApiContractCreateRekening,
        userPreferences: UserPreferencesImpl
    ): PasswordRepository {
        return PasswordRepository(apiService, userPreferences)
    }

    @Singleton
    @Provides
    fun provideUserMpinRepository (
        apiService: ApiContractCreateRekening,
        userPreferences: UserPreferencesImpl
    ): MpinRepository {
        return MpinRepository(apiService, userPreferences)
    }
}