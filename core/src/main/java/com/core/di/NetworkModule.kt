package com.core.di

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.CountDownTimer
import android.preference.PreferenceManager
import androidx.core.app.ActivityCompat.finishAffinity
import androidx.core.content.ContextCompat.startActivity
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
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
// Define Network Client Here
class NetworkModule {
    companion object{
        private const val  BASE_URL ="https://81fc-103-189-94-178.ngrok-free.app/"
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
        val token = sharedPreferences.getString("token", null)
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
    fun provideRetrofit(okHttpClient: OkHttpClient) : Retrofit {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(provideHttpLoggingInterceptor())
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(okHttpClient)
            .build()
    }


    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit): ApiContractLogin =
        retrofit.create(ApiContractLogin::class.java)

    @Singleton
    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

//    @Singleton
//    @Provides
//    fun scheduleTokenCheck() {
//    CoroutineScope(Dispatchers.IO).launch {
//        while (true) {
//            delay(60000) // Cek setiap menit, sesuaikan dengan kebutuhan Anda
//            checkTokenExpiration()
//        }
//    }
//}
//
//    @Singleton
//    @Provides
//    suspend fun checkTokenExpiration() {
//    // Dapatkan waktu kedaluwarsa dari token atau server
//    val expirationTime = // ...
//
//    if (System.currentTimeMillis() > expirationTime) {
//        // Token telah kedaluwarsa, lakukan logout
//        logout()
//    }
//}
//
//    @Singleton
//    @Provides
//    fun logout(
//        sharedPreferences: SharedPreferences
//    ) {
//        val editor = sharedPreferences.edit()
//        editor.remove("token")
//        editor.apply()
//
//    // Pindah ke layar login
//    // Contoh menggunakan Intent pada Android
//       val intent = Intent(context, Login::class.java)
//       startActivity(intent)
//
//    // Sebaiknya, hapus juga semua aktivitas sebelumnya dari tumpukan aktivitas
//    // agar pengguna tidak dapat kembali ke layar yang seharusnya hanya dapat diakses setelah login.
//       finishAffinity()
//    }

}