package com.github.frayeralex.weather.providers

import com.github.frayeralex.weather.BuildConfig
import com.github.frayeralex.weather.api.ApiClient
import com.github.frayeralex.weather.api.interceptors.AuthInterceptor
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class ServiceProvider {
    private val moshi = Moshi.Builder().build()
    private val okHttpClient by lazy { initOkHttpClient() }
    private val retrofitClient by lazy { initRetrofit(moshi, okHttpClient) }
    private val apiClient by lazy { initApiClient(retrofitClient) }

    private fun initApiClient(retrofit: Retrofit): ApiClient {
        return ApiClient(retrofit)
    }

    private fun initRetrofit(moshi: Moshi, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    //TODO add Chuck

    private fun initOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .protocols(listOf(Protocol.HTTP_1_1))
            .addInterceptor(AuthInterceptor())
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

    fun provideOkHttpClientClient(): OkHttpClient = okHttpClient
    fun provideRetrofitClient(): Retrofit = retrofitClient
    fun provideApiClient(): ApiClient = apiClient
}