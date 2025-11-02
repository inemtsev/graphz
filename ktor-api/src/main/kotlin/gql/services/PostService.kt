package com.eventslooped.gql.services

import com.expediagroup.graphql.generator.annotations.GraphQLDescription
import com.expediagroup.graphql.server.extensions.getValuesFromDataLoader
import com.expediagroup.graphql.server.operations.Query
import graphql.schema.DataFetchingEnvironment

/**
 * Get posts by Ids
 */
class PostService : Query {
    @GraphQLDescription("Get posts by id or user")
    suspend fun getPostsByIds(postSearchParams: PostSearchParams, dfe: DataFetchingEnvironment) {
        dfe.getValuesFromDataLoader<>()
    }

    data class PostSearchParams(val ids: List<Int>, val userId: Int? = null)
}
