package com.eventslooped.gql.clients

import com.eventslooped.gql.models.Comment
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.get

class CommentsClient {
    suspend fun getComments(id: Int) = client.get("/comments?postId=$id").body<List<Comment>>()

    companion object {
        val client = HttpClient(OkHttp) {
            defaultRequest {
                host = "jsonplaceholder.typicode.com"
            }
        }
    }
}