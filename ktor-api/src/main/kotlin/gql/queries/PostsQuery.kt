package com.eventslooped.gql.queries

import com.eventslooped.gql.models.Post
import com.expediagroup.graphql.generator.annotations.GraphQLDescription
import com.expediagroup.graphql.server.extensions.getValuesFromDataLoader
import com.expediagroup.graphql.server.operations.Query
import graphql.schema.DataFetchingEnvironment
import java.util.concurrent.CompletableFuture

/**
 * Get posts by Ids or Users
 */
class PostsQuery : Query {
    @GraphQLDescription("Get posts by ids or users")
    fun getPostsByIds(params: PostSearchParams, dfe: DataFetchingEnvironment): CompletableFuture<List<Post>> =
        when {
            params.ids.isNotEmpty() -> dfe.getValuesFromDataLoader("POST_LOADER_BY_IDS", params.ids)

            params.userIds.isNotEmpty() -> dfe.getValuesFromDataLoader("POST_LOADER_BY_USER_ID", params.userIds)

            else -> CompletableFuture.completedFuture(emptyList())
        }

    data class PostSearchParams(val ids: List<Int> = emptyList(), val userIds: List<Int> = emptyList())
}
