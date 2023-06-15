package com.example.proba.retrofit

import okhttp3.internal.concurrent.Task
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface MainApi {
    @PUT("task")
    suspend fun getProductById(@Path("id") id:Int): Task

    @POST("login")
    suspend fun auth(@Body authRequest: AuthRequest) :User // функция авторизации (3)

    @POST("task")
    suspend fun newTask(@Body  task: Task)

    @GET("task")
    suspend fun getAllProducts(): Products // получить все продукты (4)






}

