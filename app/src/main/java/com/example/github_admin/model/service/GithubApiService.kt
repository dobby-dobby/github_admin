package com.example.github_admin.model.service

import com.example.github_admin.model.User
import retrofit2.http.GET

interface GithubApiService {

    @GET("users?per_page=20&since=100")
    suspend fun getListUser(): List<User>
}