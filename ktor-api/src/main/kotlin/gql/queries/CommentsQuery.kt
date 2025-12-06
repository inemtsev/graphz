package com.eventslooped.gql.queries

import com.eventslooped.gql.models.Comment
import com.expediagroup.graphql.generator.annotations.GraphQLDescription
import com.expediagroup.graphql.server.extensions.getValuesFromDataLoader
import com.expediagroup.graphql.server.operations.Query
import graphql.schema.DataFetchingEnvironment
import java.util.concurrent.CompletableFuture

/**
 * Get comments by Ids or Posts
 */
class CommentsQuery : Query {
    @GraphQLDescription("Get comments by ids or posts")
    fun getCommentsByIds(params: CommentsSearchParams, dfe: DataFetchingEnvironment): CompletableFuture<List<Comment>> =
        when {
            params.ids.isNotEmpty() -> dfe.getValuesFromDataLoader("COMMENTS_LOADER_BY_IDS", params.ids)
            else -> CompletableFuture.completedFuture(emptyList())
        }

    data class CommentsSearchParams(val ids: List<Int> = emptyList(), val postIds: List<Int> = emptyList())
}