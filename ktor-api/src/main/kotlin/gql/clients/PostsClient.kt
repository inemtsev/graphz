package com.eventslooped.gql.clients

import com.eventslooped.gql.models.Post
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.request.get

class PostsClient {
    suspend fun getPosts() = client.get("https://jsonplaceholder.typicode.com/posts").body<List<Post>>()

    suspend fun getPost(id: Int) = client.get("https://jsonplaceholder.typicode.com/posts/$id").body<Post>()

    companion object {
        val client = HttpClient(OkHttp)
    }
}
