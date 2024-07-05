package com.example.taskmanager.network

import com.example.taskmanager.data.ToDoTaskResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface ToDoService {
    @GET("task")
    suspend fun searchPhotos(
        @Query("name") name: String,
        @Query("content") content: String,
        @Query("per_page") perPage: Int
    ): ToDoTaskResponse

}

object MangeTaskApi{
    private const val BASE_URL = "https://api.unsplash.com/"

    fun create(): ToDoService {
        val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

        val client = OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ToDoService::class.java)
    }
}