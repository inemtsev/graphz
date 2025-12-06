package com.eventslooped.gql.queries

import com.eventslooped.gql.models.Comments
import com.expediagroup.graphql.generator.annotations.GraphQLDescription
import com.expediagroup.graphql.server.operations.Query
import graphql.schema.DataFetchingEnvironment
import java.util.concurrent.CompletableFuture

class CommentsQuery : Query {
    @GraphQLDescription("Get comments by ids or posts")
    fun getCommentsByIds(commentsSearchParams: CommentsSearchParams, dfe: DataFetchingEnvironment): CompletableFuture<List<Comments>> {
        when {
            else -> return CompletableFuture.completedFuture(emptyList())
        }
    }

    data class CommentsSearchParams(val ids: List<Int> = emptyList(), val postIds: List<Int> = emptyList())
}