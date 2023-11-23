package com.digimbanking.Di

import android.content.Context
import com.core.data.lokal.preferences.UserPreferencesImpl
import com.core.data.repositories.OtpRepository
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
}