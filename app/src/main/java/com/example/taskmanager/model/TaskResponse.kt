package com.example.taskmanager.model
import com.google.gson.annotations.SerializedName

data class Task(
    @SerializedName("name")
    val totalPages: String,
    @SerializedName("content")
    val content: String
)
