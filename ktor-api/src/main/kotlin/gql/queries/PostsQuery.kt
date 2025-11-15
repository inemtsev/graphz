package com.eventslooped.gql.queries

import com.eventslooped.gql.models.Post
import com.expediagroup.graphql.generator.annotations.GraphQLDescription
import com.expediagroup.graphql.server.extensions.getValuesFromDataLoader
import com.expediagroup.graphql.server.operations.Query
import graphql.schema.DataFetchingEnvironment
import java.util.concurrent.CompletableFuture

/**
 * Get posts by Ids
 */
class PostsQuery : Query {
    @GraphQLDescription("Get posts by id or user")
    fun getPostsByIds(postSearchParams: PostSearchParams, dfe: DataFetchingEnvironment): CompletableFuture<List<Post>> =
        when {
            postSearchParams.ids.isNotEmpty() -> dfe.getValuesFromDataLoader("POST_LOADER_BY_IDS", postSearchParams.ids)

            else -> CompletableFuture.completedFuture(emptyList())
        }

    data class PostSearchParams(val ids: List<Int> = emptyList(), val userId: Int? = null)
}
