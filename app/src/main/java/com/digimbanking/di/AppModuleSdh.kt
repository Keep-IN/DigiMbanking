package com.digimbanking.di

import android.content.Context
import com.core.data.local.preferences.UserPreferencesSdhImplSdh
import com.core.data.repositories.EmailRepositorySdh
import com.core.data.repositories.MPINRepositorySdh
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
object AppModuleSdh {

    @Singleton
    @Provides
    fun provideUserPreferencesDataStore(@ApplicationContext context: Context): UserPreferencesSdhImplSdh {
        return UserPreferencesSdhImplSdh(context)
    }

    @Singleton
    @Provides
    fun provideUserEmailRepositorySdh (
        apiService: ApiContractAdaRekening,
        userPreferences: UserPreferencesSdhImplSdh
    ): EmailRepositorySdh {
        return EmailRepositorySdh(apiService, userPreferences)
    }

    @Singleton
    @Provides
    fun provideUserPasswordRepositorySdh (
        apiService: ApiContractAdaRekening,
        userPreferences: UserPreferencesSdhImplSdh
    ): PasswordRepositroySdh {
        return PasswordRepositroySdh(apiService, userPreferences)
    }

    @Singleton
    @Provides
    fun provideUserMPINRepositorySdh (
        apiService: ApiContractAdaRekening,
        userPreferences: UserPreferencesSdhImplSdh
    ): MPINRepositorySdh {
        return MPINRepositorySdh(apiService, userPreferences)
    }
}