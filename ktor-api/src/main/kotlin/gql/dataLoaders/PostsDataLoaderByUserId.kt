package com.eventslooped.gql.dataLoaders

import com.eventslooped.gql.clients.PostsClient
import com.eventslooped.gql.models.Post
import com.expediagroup.graphql.dataloader.KotlinDataLoader
import com.expediagroup.graphql.generator.extensions.get
import graphql.GraphQLContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.future.future
import org.dataloader.DataLoader
import org.dataloader.DataLoaderFactory
import kotlin.coroutines.EmptyCoroutineContext

val PostsDataLoaderByUserId = object : KotlinDataLoader<Int, List<Post?>> {
    override val dataLoaderName = "POST_LOADER_BY_USER_ID"

    override fun getDataLoader(graphQLContext: GraphQLContext): DataLoader<Int, List<Post?>> {
        val postsClient = PostsClient()

        return DataLoaderFactory.newDataLoader { userIds, batchDataLoaderEnvironment ->
            val coroutineScope = batchDataLoaderEnvironment.getContext<GraphQLContext>()?.get<CoroutineScope>()
                ?: CoroutineScope(EmptyCoroutineContext)

            val requests = buildList {
                userIds.forEach { userId ->
                    add(coroutineScope.async { postsClient.getPosts().filter { p -> p.userId == userId }.toList() })
                }
            }

            coroutineScope.future { requests.awaitAll() }
        }
    }
}
