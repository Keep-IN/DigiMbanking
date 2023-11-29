package com.digimbanking.Di

import android.content.Context
import android.content.SharedPreferences
import com.core.data.local.preferences.UserPreferencesImpl
import com.core.data.repositories.LoginRepository
import com.core.data.repositories.ProfilRepository
import com.core.data.repositories.UbahPwRepository
import com.core.di.ApiContractLogin
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
    fun provideUserLoginRepository (
        apiService : ApiContractLogin,
        userPreferences: UserPreferencesImpl
    ): LoginRepository {
        return LoginRepository(apiService, userPreferences)
    }

    @Singleton
    @Provides
    fun provideUserUbahPassword(
        apiService: ApiContractLogin,
        userPreferences: UserPreferencesImpl,
        sharedPreferences: SharedPreferences
    ):  UbahPwRepository {
        return UbahPwRepository(apiService, userPreferences, sharedPreferences)
    }

    @Singleton
    @Provides
    fun provideUserProfil(
        apiService: ApiContractLogin,
        userPreferences: UserPreferencesImpl,
        sharedPreferences: SharedPreferences
    ): ProfilRepository {
        return ProfilRepository(apiService, userPreferences, sharedPreferences)
    }
}