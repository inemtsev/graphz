package com.eventslooped.gql.models

data class Comment(val id: Int, val postId: Int, val name: String, val email: String, val body: String)
