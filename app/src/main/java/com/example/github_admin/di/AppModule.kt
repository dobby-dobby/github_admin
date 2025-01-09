package com.example.github_admin.di

import com.example.github_admin.model.repository.GithubAdminRepository
import com.example.github_admin.model.service.GithubApiService
import com.example.github_admin.model.service.HeaderInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HeaderInterceptor())
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl("https://api.github.com/").client(okHttpClient).addConverterFactory(
            GsonConverterFactory.create()
        ).build()
    }

    @Provides
    @Singleton
    fun provideGithubApiService(retrofit: Retrofit): GithubApiService {
        return retrofit.create(GithubApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideGithubAdminRepository(apiService: GithubApiService): GithubAdminRepository {
        return GithubAdminRepository(apiService)
    }
}