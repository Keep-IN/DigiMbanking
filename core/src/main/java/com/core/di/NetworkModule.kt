package com.core.di

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
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
        private const val  BASE_URL ="https://81fc-103-189-94-178.ngrok-free.app/api/v1/"
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
        sharedPreferences: SharedPreferences
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor { chain ->
                val requestBuilder = chain.request()
                    .newBuilder()
                    .header("Authorization", "Bearer $token")
                    .build()
                chain.proceed(requestBuilder)
            }
            .build()
    }

    @Singleton
    @Provides
    fun getAuthToken(sharedPreferences: SharedPreferences): String {
        return sharedPreferences.getString("auth_token", "") ?: ""
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

}