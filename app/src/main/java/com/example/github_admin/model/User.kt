package com.example.github_admin.model

data class User(
    val avatar_url: String,
    val html_url: String,
    val login: String,
    val gravatar_id: String,
    val id: Int,
    val location: String,
    val followers: Int,
    val following: Int
)