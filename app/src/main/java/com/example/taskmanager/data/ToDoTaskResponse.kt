package com.example.taskmanager.data
import com.google.gson.annotations.SerializedName

data class ToDoTaskResponse(
    @SerializedName("name")
    val totalPages: String,
    @SerializedName("content")
    val content: String
)
