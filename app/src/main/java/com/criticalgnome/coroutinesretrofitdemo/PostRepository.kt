package com.criticalgnome.coroutinesretrofitdemo

import com.criticalgnome.coroutinesretrofitdemo.model.Post

class PostRepository {

    private val api = JsonPlaceholderApi.getApi()

    suspend fun getPosts(): Result<List<Post>> =
        try {
            Result.Success(api.getPosts().await())
        } catch (e: Exception) {
            Result.Error(e)
        }
}
