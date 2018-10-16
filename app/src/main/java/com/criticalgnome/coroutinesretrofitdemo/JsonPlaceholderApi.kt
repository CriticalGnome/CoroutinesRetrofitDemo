package com.criticalgnome.coroutinesretrofitdemo

import com.criticalgnome.coroutinesretrofitdemo.model.Post
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import kotlinx.coroutines.experimental.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface JsonPlaceholderApi {

    @GET("/posts")
    fun getPosts(): Deferred<List<Post>>

    companion object {
        private const val BASE_URL = "https://jsonplaceholder.typicode.com"
        fun getApi(): JsonPlaceholderApi = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(JsonPlaceholderApi::class.java)
    }
}