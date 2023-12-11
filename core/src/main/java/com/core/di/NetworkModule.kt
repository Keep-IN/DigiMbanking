package com.core.di

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.core.content.ContextCompat.startActivity
import com.core.data.local.preferences.UserPreferencesImpl
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
// Define Network Client Here
class NetworkModule {
    companion object{
        private const val  BASE_URL ="https://73ae-36-73-119-245.ngrok-free.app/api/v1/"
        private const val token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJrZXZpbkBnbWFpbC5jb20iLCJpYXQiOjE3MDEyMjUzNjUsImV4cCI6MTcwMTMxMTc2NX0.eIM2PokwcyLjcmZXX_m4d8KE6N9Kjh3y_5gXUK75GuU"
    }
    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        sharedPreferences: SharedPreferences,
        userPreferencesImpl: UserPreferencesImpl
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor { chain ->
                val requestBuilder = chain.request()
                    .newBuilder()
                    .header("Authorization", "Bearer ${getAuthToken(sharedPreferences)}")
                    .build()
                val response = chain.proceed(requestBuilder)

                if (response.code == 401) {
                    // Token expired, lakukan proses logout di sini
                    // Misalnya: AuthManager.logout()
                    CoroutineScope(Dispatchers.Main).launch {
                        userPreferencesImpl.logout()
                    }
                    // Setelah logout, Anda dapat membuka halaman login atau melakukan tindakan lain
                }

                response
            }
            .build()
    }

    @Singleton
    @Provides
    fun getAuthToken(sharedPreferences: SharedPreferences): String {
        return sharedPreferences.getString("token", "") ?: ""
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient) : Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(okHttpClient)
            .build()

    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit): ApiContractCreateRekening =
        retrofit.create(ApiContractCreateRekening::class.java)

    @Singleton
    @Provides
    fun provideApis(retrofit: Retrofit): ApiContractDukcapil =
        retrofit.create(ApiContractDukcapil::class.java)

    @Singleton
    @Provides
    fun provideApiTransfer(retrofit: Retrofit): ApiContractTransfer =
        retrofit.create(ApiContractTransfer::class.java)

    @Singleton
    @Provides
    fun provideApisdh(retrofit: Retrofit): ApiContractAdaRekening =
        retrofit.create(ApiContractAdaRekening::class.java)


    @Singleton
    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

    @Singleton
    @Provides
    fun provideApiRiwayat(retrofit: Retrofit): ApiContractRiwayat =
        retrofit.create(ApiContractRiwayat::class.java)
    @Singleton
    @Provides
    fun provideApiLogin(retrofit: Retrofit): ApiContractLogin =
        retrofit.create(ApiContractLogin::class.java)

}