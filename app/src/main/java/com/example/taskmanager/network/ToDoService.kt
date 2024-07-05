package com.example.taskmanager.network

import com.example.taskmanager.model.Task
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


private const val BASE_URL =
    "https://android-kotlin-fun-mars-server.appspot.com"


private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

/**
 * Retrofit service object for creating api calls
 */
interface ToDoTaskApiService {
    @GET("tasks")
    suspend fun getTasks(): List<Task>
}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object TaskManagerApi {
    val retrofitService: ToDoTaskApiService by lazy {
        retrofit.create(ToDoTaskApiService::class.java)
    }
}