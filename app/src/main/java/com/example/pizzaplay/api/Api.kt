package com.example.pizzaplay.api

import com.example.pizzaplay.model.PizzaModel
import com.example.pizzaplay.model.UserModel
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

class Api {
    companion object {
        const val BASE_URL = "http://152.70.163.213:3000"

        val apiMethods: ApiMethods =
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiMethods::class.java)

    }

    interface ApiMethods {
        @GET("/getPizza")
        fun getPizza(): Call<List<PizzaModel>>

        @GET("/getUsers")
        fun getUsers(): Call<UserModel>

        @GET("/getUserByUsername")
        fun getUserByUsername(@Query("username") username: String): Call<UserModel>

        @POST("/createUser")
        fun createUser(@Body user: UserModel): Call<String>


    }
}