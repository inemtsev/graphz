package com.eventslooped.gql.services

import com.expediagroup.graphql.generator.annotations.GraphQLDescription
import com.expediagroup.graphql.server.operations.Query
import graphql.schema.DataFetchingEnvironment

/**
 * Get posts by Ids
 */
class PostService : Query {
    @GraphQLDescription("Get posts by id")
    suspend fun getPostsByIds(postSearchParams: PostSearchParams, dfe: DataFetchingEnvironment) {

    }

    data class PostSearchParams(val ids: List<Int>)
}