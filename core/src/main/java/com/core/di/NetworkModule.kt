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
        private const val  BASE_URL ="https://e441-103-189-94-178.ngrok-free.app/api/v1/"
        private const val token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJrZXZpbkBnbWFpbC5jb20iLCJpYXQiOjE3MDEzMTQ5NDksImV4cCI6MTcwMTQwMTM0OX0.__CKBQY1TO4Ru7hYNtcp-N9TBRuIBO2r16CxO6F8E3A"
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
                    .header("Authorization", "Bearer ${getAuthToken(sharedPreferences)}")
                    .build()
                chain.proceed(requestBuilder)
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