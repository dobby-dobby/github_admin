package com.example.github_admin.model.service

import com.example.github_admin.model.DetailUser
import com.example.github_admin.model.User
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApiService {

    @GET("users")
    suspend fun getListUser(@Query("per_page") perpage : Int,
                            @Query("since") since: Int): List<User>

    @GET("users/{loginUser}")
    suspend fun getDetailUser(@Path("loginUser") loginUser: String): DetailUser
}