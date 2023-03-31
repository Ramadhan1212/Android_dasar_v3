package com.ipoy.android_dasar_v3.connection

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import com.ipoy.android_dasar_v3.BuildConfig
import retrofit2.converter.gson.GsonConverterFactory


object Connection {
    private const val BASE_URL = "https://api.github.com/"

    val authInterceptor = Interceptor { chain ->
        val req = chain.request()
        val requestHeaders = req.newBuilder()
            .addHeader("Authorization", "${BuildConfig.SECRET_KEY}")
            .build()
        chain.proceed(requestHeaders)
    }

    val client = OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    val apiIns = retrofit.create(Query::class.java)
}