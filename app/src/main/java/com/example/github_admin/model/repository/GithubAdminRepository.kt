package com.example.github_admin.model.repository

import com.example.github_admin.model.DetailUser
import com.example.github_admin.model.User
import com.example.github_admin.model.service.GithubApiService
import javax.inject.Inject

class GithubAdminRepository @Inject constructor(private val apiService: GithubApiService) {

    suspend fun getListUser(perPage: Int, since: Int): List<User> {
        return apiService.getListUser(perPage, since)
    }

    suspend fun getDetailUser(userLogin: String): DetailUser {
        return apiService.getDetailUser(userLogin)
    }
}