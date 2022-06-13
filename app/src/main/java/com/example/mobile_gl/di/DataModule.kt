package com.example.mobile_gl.di

import com.example.mobile_gl.data.MainDataSource
import com.example.mobile_gl.data.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    val API_URL = "http://private-f0eea-mobilegllatam.apiary-mock.com/"

    @ApiMobileGL
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient) = Retrofit.Builder()
        .baseUrl(API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    @Provides
    fun provideOkHttpClient() = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    @Provides
    fun provideApiService(@ApiMobileGL retrofit: Retrofit) =
        retrofit.create(ApiService::class.java)

    @Provides
    fun provideApiDataSource(apiService: ApiService): MainDataSource{
        return MainDataSource((apiService))
    }
}