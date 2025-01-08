package com.example.github_admin.model.repository

import com.example.github_admin.model.User
import com.example.github_admin.model.service.GithubApiService
import javax.inject.Inject

class GithubAdminRepository @Inject constructor(private val apiService: GithubApiService) {

    suspend fun getListUser(): List<User> {
        return apiService.getListUser()
    }
}