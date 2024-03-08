package com.example.socialnetwork.Models



data class Post(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int,
    var isLiked: Boolean = false
)